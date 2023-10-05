package py.com.prestamos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuota {
    private Integer numeroCuota;

    private String fechaVencimiento;

    private BigDecimal montoAPagar;
}
