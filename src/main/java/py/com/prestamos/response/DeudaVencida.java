package py.com.prestamos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DeudaVencida {
    private String nroCuenta;
    private int nroCuota;
    private Date fechaVencimiento;
    private BigDecimal montoTotalCobrar;
}
