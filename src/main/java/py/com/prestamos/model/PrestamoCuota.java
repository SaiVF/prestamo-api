package py.com.prestamos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrestamoCuota {

    private Integer nroCuota;

    private Date fecVencimiento;

    private BigDecimal montoPagar;
}
