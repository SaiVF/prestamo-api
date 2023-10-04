package py.com.prestamos.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
@Data
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
