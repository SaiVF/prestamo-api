package py.com.prestamos.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Cuota {
    private Integer numeroCuota;

    private String fechaVencimiento;

    private BigDecimal montoAPagar;
}
