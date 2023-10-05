package py.com.prestamos.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import py.com.prestamos.model.*;
import py.com.prestamos.repository.PrestamoRepository;
import py.com.prestamos.request.ConsultaPorDocumentoRequest;
import py.com.prestamos.request.ConsultaPrestamoDetalleCuotasRequest;
import py.com.prestamos.request.DatosClienteRequest;
import py.com.prestamos.response.*;

import java.math.BigDecimal;
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
    public PrestamosPersona consultarPrestamoDocumento(Integer paisDocumento, Integer tipoDocumento, String numeroDocumento, Integer moneda) throws Exception {
        try {
            ConsultaPorDocumentoRequest documento = new ConsultaPorDocumentoRequest();
            documento.setNumeroDocumento(numeroDocumento);
            documento.setCodigoPais(paisDocumento);
            documento.setCodigoTipoDoc(tipoDocumento);
            OperacionesPrestamoResponse operaciones = this.descartarPrestamosCanceladosAntesCierre(prestamoRepository.obtenerPrestamoDocumento(documento, moneda));
            ordenarPorFechaVencimiento(operaciones.getOperaciones());
            consultarPrestamoDetalle(operaciones);
            if (Objects.nonNull(operaciones.getOperaciones()) && !operaciones.getOperaciones().isEmpty()) {
            }
            return parsePrestamoPersona(operaciones);
        } catch (Exception e) {
            log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    private OperacionesPrestamoResponse descartarPrestamosCanceladosAntesCierre(OperacionesPrestamoResponse prestamos){
        if(prestamos!=null && prestamos.getOperaciones()!=null){
            List<PrestamoOperacionDTO> noCancelados = new ArrayList<>();
            for (PrestamoOperacionDTO prestamo : prestamos.getOperaciones()) {
                if(BigDecimal.ZERO.compareTo(prestamo.getSaldo())!=0){
                    noCancelados.add(prestamo);
                }
            }
            prestamos.setOperaciones(noCancelados);
        }
        return prestamos;
    }
    private void ordenarPorFechaVencimiento(List<PrestamoOperacionDTO> prestamos){
        if(prestamos!=null){
            Collections.sort(prestamos, Comparator.comparing(o1->o1.getFechaVencimiento()));
        }
    }

    private void consultarPrestamoDetalle(OperacionesPrestamoResponse operaciones) throws Exception {
        Date fechaApertura = getFechaApertura();
        for (PrestamoOperacionDTO o: operaciones.getOperaciones()) {
            try {
                PrestamoDetalleDTO  prestamoDetalle = prestamoRepository.obtenerCuotasPrestamo(buildDetalleRequest(o, fechaApertura));
                o.setDetalle(prestamoDetalle);
            } catch (Exception e) {
                log.error(e);
                throw new Exception(e.getMessage());
            }
        }
    }

    private Date getFechaApertura() {
        return prestamoRepository.consultarFechaApertura();
    }
    private ConsultaPrestamoDetalleCuotasRequest buildDetalleRequest(PrestamoOperacionDTO prestamoOperacionDTO, Date fechaApertura) throws Exception {
        ConsultaPrestamoDetalleCuotasRequest rq = new ConsultaPrestamoDetalleCuotasRequest();
        rq.setFecha(fechaApertura);
        rq.setModulo(prestamoOperacionDTO.getModulo());
        rq.setMoneda(prestamoOperacionDTO.getMoneda());
        rq.setNumeroOperacion(prestamoOperacionDTO.getNumeroOperacion());
        rq.setPapel(prestamoOperacionDTO.getPapel());
        rq.setSubOperacion(prestamoOperacionDTO.getSubOperacion());
        rq.setSucursal(prestamoOperacionDTO.getSucursal());
        rq.setTipoOperacion(prestamoOperacionDTO.getTipoOperacion());
        return rq;
    }
    @Override
    public PrestamosPersona consultarPrestamoCuenta(Integer cuenta, Integer moneda) {
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
