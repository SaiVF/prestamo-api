package py.com.prestamos.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import py.com.prestamos.model.*;
import py.com.prestamos.repository.PrestamoRepository;
import py.com.prestamos.request.ConsultaPorDocumentoRequest;
import py.com.prestamos.request.ConsultaPrestamoDetalleCuotasRequest;
import py.com.prestamos.response.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PrestamoServiceImpl implements PrestamoService{

    private final PrestamoRepository prestamoRepository;
    private static final String DATE_FORMAT = "dd/MM/yyyy";

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
            OperacionesPrestamoResponse operaciones = descartarPrestamosCanceladosAntesCierre(prestamoRepository.obtenerPrestamoDocumento(documento, moneda));
            ordenarPorFechaVencimiento(operaciones.getOperaciones());
            consultarPrestamoDetalle(operaciones);
            return parsePrestamoPersona(operaciones);
        } catch (Exception e) {
            log.error("Error en consultarPrestamoDocumento", e);
            throw new Exception(e.getMessage());
        }
    }

    private OperacionesPrestamoResponse descartarPrestamosCanceladosAntesCierre(OperacionesPrestamoResponse prestamos) {
        if (prestamos != null && prestamos.getOperaciones() != null) {
            prestamos.getOperaciones().removeIf(prestamo -> BigDecimal.ZERO.compareTo(prestamo.getSaldo()) == 0);
        }
        return prestamos;
    }

    private void ordenarPorFechaVencimiento(List<PrestamoOperacionDTO> prestamos) {
        if (Objects.nonNull(prestamos)) {
            prestamos.sort(Comparator.comparing(PrestamoOperacionDTO::getFechaVencimiento));
        }
    }

    private void consultarPrestamoDetalle(OperacionesPrestamoResponse operaciones) throws Exception {
        LocalDate fechaApertura = getFechaApertura();
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

    private LocalDate getFechaApertura() {
        return prestamoRepository.consultarFechaApertura();
    }
    private ConsultaPrestamoDetalleCuotasRequest buildDetalleRequest(PrestamoOperacionDTO prestamoOperacionDTO, LocalDate fechaApertura) throws Exception {
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
        List<PrestamoOperacionDTO> prestamos = Optional.ofNullable(operaciones.getOperaciones()).orElse(Collections.emptyList());

        for (PrestamoOperacionDTO o : prestamos) {
            Prestamo p = parsePrestamo(o);
            prestamoList.add(p);
        }

        ordenarYAsignarPrestamos(prestamoList, prestamosPersona);

        return prestamosPersona;
    }

    private Prestamo parsePrestamo(PrestamoOperacionDTO o) {
        Prestamo p = new Prestamo();

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
        return p;
    }

    private void ordenarYAsignarPrestamos(List<Prestamo> prestamoList, PrestamosPersona prestamosPersona) {
        ordenarPorFechaVencimientoCuota(prestamoList);
        prestamosPersona.setPrestamos(prestamoList);
    }

    private String cambiarFormatoFecha(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT);
        return sdf2.format(date);
    }
    private List<Cuota> parseCuotas(PrestamoDetalleDTO prestamoDetalle) {
        return prestamoDetalle.getPrestamoCuotas()
                .stream()
                .map(pc -> {
                    Cuota cuota = new Cuota();
                    cuota.setFechaVencimiento(cambiarFormatoFecha(pc.getPrev().getFechaVencimiento()));
                    cuota.setNumeroCuota(pc.getPrev().getNumeroCuota());
                    cuota.setMontoAPagar(pc.getPrev().getImporteAPagar());
                    return cuota;
                })
                .collect(Collectors.toList());
    }
    private Date vencimientoCuota(PrestamoDetalleDTO prestamoDetalle) {
        if (prestamoDetalle.getPrestamoCuotas() != null && !prestamoDetalle.getPrestamoCuotas().isEmpty()) {
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
