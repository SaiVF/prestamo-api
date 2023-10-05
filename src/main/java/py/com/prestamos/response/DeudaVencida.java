package py.com.prestamos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeudaVencida {
    private String nroCuenta;
    private int nroCuota;
    private Date fechaVencimiento;
    private BigDecimal montoTotalCobrar;
}
