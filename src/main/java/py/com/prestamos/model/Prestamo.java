package py.com.prestamos.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Prestamo {
    private Long numeroPrestamo;

    private Integer modulo;

    private Integer sucursal;

    private Integer moneda;

    private String monedaLetras;

    private Integer papel;

    private Long operacion;

    private Integer subCuenta;

    private Integer tipoOperacion;

    private String fechaVencimiento;

    private BigDecimal monto;

    private List<Cuota> cuotas;

    private Integer cantidadCuotas;

    private Date fechaVencimientoCuota;
}
