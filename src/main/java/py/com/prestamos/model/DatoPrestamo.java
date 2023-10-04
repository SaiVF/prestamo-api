package py.com.prestamos.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DatoPrestamo {
    private String nroPrestamo;
    private String moneda;
    private BigDecimal montoCuota;
    private Date fecVencimiento;
    private String nroDocumento;
    private String nombreCompleto;
}
