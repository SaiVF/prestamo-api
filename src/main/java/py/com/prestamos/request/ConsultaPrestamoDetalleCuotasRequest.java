package py.com.prestamos.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ConsultaPrestamoDetalleCuotasRequest {

    private LocalDate fecha;

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
