package py.com.prestamos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaPrestamoDetalleCuotasRequest {
    private Date fecha;

    private Integer canal;

    private Long numeroOperacion;

    private Integer subOperacion;

    private Integer tipoOperacion;

    private Integer moneda;

    private Integer modulo;

    private Integer papel;

    private Long nroCuenta;

    private Integer sucursal;

    private Integer empresa;
}
