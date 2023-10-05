package py.com.prestamos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperacionesPrestamoResponse {
    @XmlElement(name = "CAB")
    private PrestamoCabeceraDTO cabecera;

    @XmlElementWrapper(name = "LIN")
    @XmlElement(name = "OPE")
    private List<PrestamoOperacionDTO> operaciones;

    @XmlTransient
    private String nombre;

    @XmlTransient
    private String apellido;
}
