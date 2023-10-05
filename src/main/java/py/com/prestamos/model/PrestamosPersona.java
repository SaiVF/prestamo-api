package py.com.prestamos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamosPersona {
    public static final String NOCTA = "NOCTA";

    private String nombres;

    private String apellidos;

    private Long numeroCuenta;

    private List<Prestamo> prestamos;
}
