package py.com.prestamos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatoPrestamo {
    private String nroPrestamo;

    private String moneda;

    private BigDecimal montoCuota;

    private Date fecVencimiento;

    private String nroDocumento;

    private String nombreCompleto;
}
