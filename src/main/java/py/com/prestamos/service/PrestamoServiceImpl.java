package py.com.prestamos.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import py.com.prestamos.model.*;
import py.com.prestamos.repository.PrestamoRepository;
import py.com.prestamos.request.DatosClienteRequest;
import py.com.prestamos.response.*;

import java.sql.SQLDataException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Log4j2
public class PrestamoServiceImpl implements PrestamoService{

    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }
@Override
    public DatosClienteRequest getParams(Integer moneda, String numeroDocumento, Integer paisDocumento, Integer tipoDocumento, Integer cuenta) throws Exception{
    DatosClienteRequest request = new DatosClienteRequest();
    request.setMoneda(moneda);
    request.setNumeroDocumento(numeroDocumento);
    request.setPaisDocumento(paisDocumento);
    request.setTipoDocumento(tipoDocumento);
    request.setCuenta(cuenta);
    return request;
}

    @Override
    public PrestamosPersona consultarPrestamo(DatosClienteRequest request) throws Exception {
        PrestamosPersona response;
        if(!request.getNumeroDocumento().isEmpty()){
            log.info("Se realiza la consulta por numero de documento");
            response = consultarPrestamoPorDocumento(request);
        }else{
            log.info("Se realiza la consulta por numero de cuenta");
            response = consultarPrestamoPorCuenta(request);
        }
        return response;
    }

    private PrestamosPersona consultarPrestamoPorDocumento(DatosClienteRequest request) throws Exception {
        List<PrestamoOperacionDTO> listOpDTO = new ArrayList<>();
        OperacionesPrestamoResponse operacionesPrestamoResponse = new OperacionesPrestamoResponse();
        List<DatoPrestamo> listaPrestamos = null;
        ConsultaCliente consultaCliente = new ConsultaCliente();
        consultaCliente.setCodigoPais(request.getPaisDocumento());
        consultaCliente.setCodigoTipo(request.getTipoDocumento());
        consultaCliente.setNroDocumento(request.getNumeroDocumento());
        DatosCliente datosCliente = prestamoRepository.obtenerDatoscliente(consultaCliente);
        if(datosCliente == null){
            log.error("Error al obtener datos del cliente");
            throw new SQLDataException("No se pudo obtener datos del Cliente");
        }
        /* Obtenemos la moneda por codigo */
        String mon = prestamoRepository.obtenerMonedaBcp(request.getMoneda());
        /* Obtenemos datos del prestamo */

        try {
            listaPrestamos = prestamoRepository.getPrestamoDocumento(datosCliente.getCodPersona(), mon);
        } catch (DataAccessException e) {
            log.error("Ocurrio un error al obtener datos de prestamo", e);
        }
        operacionesPrestamoResponse.setNombre(datosCliente.getPrimerNombre() + " " + (datosCliente.getSegundoNombre() != null ? datosCliente.getSegundoNombre() : ""));
        operacionesPrestamoResponse.setApellido(datosCliente.getPrimerApellido() + " " + (datosCliente.getSegundoApellido() != null ? datosCliente.getSegundoApellido() : ""));

        for (DatoPrestamo p : listaPrestamos) {
            DeudaVencida deudaVencida;
            try {
                deudaVencida = prestamoRepository.getDeudaVencidaByNroCuentaAndMoneda(p.getNroPrestamo(),request.getMoneda());
            } catch (SQLDataException throwables) {
                throw new Exception("Ocurrio un error al obtener la deuda vencida");
            }
            PrestamoOperacionDTO prestamoOperacionDTO = new PrestamoOperacionDTO();
            prestamoOperacionDTO.setNumeroOperacion(Long.valueOf(p.getNroPrestamo()));
            prestamoOperacionDTO.setMonedaLetras(p.getMoneda());
            prestamoOperacionDTO.setMoneda(request.getMoneda());
            prestamoOperacionDTO.setFechaVencimiento(deudaVencida.getFechaVencimiento());
            prestamoOperacionDTO.setSaldo(deudaVencida.getMontoTotalCobrar());
            listOpDTO.add(prestamoOperacionDTO);
        }
        operacionesPrestamoResponse.setOperaciones(listOpDTO);
        return parsePrestamoPersona(operacionesPrestamoResponse);
    }
    private PrestamosPersona consultarPrestamoPorCuenta(DatosClienteRequest request){
        return null;
    }

    private PrestamosPersona parsePrestamoPersona(OperacionesPrestamoResponse operaciones) {
        PrestamosPersona prestamosPersona = new PrestamosPersona();
        List<Prestamo> prestamoList = new ArrayList<>();

        if (operaciones != null && operaciones.getOperaciones() != null) {
            for (PrestamoOperacionDTO o : operaciones.getOperaciones()) {
                Prestamo p = new Prestamo();

                if (o != null && o.getDetalle() != null) {
                    p.setCantidadCuotas(o.getDetalle().getCantidadCuotas());
                    p.setNumeroPrestamo(o.getNumeroOperacion());
                    p.setModulo(o.getModulo());
                    p.setMoneda(o.getMoneda());
                    p.setMonedaLetras(o.getMonedaLetras());
                    p.setSucursal(o.getSucursal());
                    p.setPapel(o.getPapel());
                    p.setOperacion(o.getNumeroOperacion());
                    p.setSubCuenta(o.getSubOperacion());
                    p.setTipoOperacion(o.getTipoOperacion());
                    p.setFechaVencimiento(cambiarFormatoFecha(o.getFechaVencimiento()));
                    p.setMonto(o.getSaldo());
                    p.setCuotas(parseCuotas(o.getDetalle()));
                    p.setFechaVencimientoCuota(vencimientoCuota(o.getDetalle()));
                    prestamoList.add(p);
                } else {
                    // Manejar el caso donde o o o.getDetalle() es nulo
                    // Puedes ignorar este prestamo o tomar alguna acción según tus necesidades
                }
            }

            ordenarPorFechaVencimientoCuota(prestamoList);
            prestamosPersona.setPrestamos(prestamoList);
        } else {
            // Manejar el caso donde operaciones o operaciones.getOperaciones() es nulo
            // Puedes asignar una lista vacía o tomar alguna acción según tus necesidades
            prestamosPersona.setPrestamos(new ArrayList<>());
        }

        return prestamosPersona;
    }

    private String cambiarFormatoFecha(Date date) {
        if (date== null) {
            return "";
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        return sdf2.format(date);
    }
    private List<Cuota> parseCuotas(PrestamoDetalleDTO prestamoDetalle){

        List<Cuota> cuotas = new ArrayList<>();
        for (PrestamoCuotaDTO pc: prestamoDetalle.getPrestamoCuotas()) {
            Cuota cuota = new Cuota();
            cuota.setFechaVencimiento(cambiarFormatoFecha(pc.getPrev().getFechaVencimiento()));
            cuota.setNumeroCuota(pc.getPrev().getNumeroCuota());
            cuota.setMontoAPagar(pc.getPrev().getImporteAPagar());
            cuotas.add(cuota);
        }
        return cuotas;
    }
    private Date vencimientoCuota(PrestamoDetalleDTO prestamoDetalle){
        if (prestamoDetalle.getPrestamoCuotas()!=null){
            return prestamoDetalle.getPrestamoCuotas().get(0).getPrev().getFechaVencimiento();
        }
        return null;
    }
    private void ordenarPorFechaVencimientoCuota(List<Prestamo> prestamos){
        if(prestamos!=null){
            Collections.sort(prestamos, Comparator.comparing(o1->o1.getFechaVencimientoCuota()));
        }
    }
}
