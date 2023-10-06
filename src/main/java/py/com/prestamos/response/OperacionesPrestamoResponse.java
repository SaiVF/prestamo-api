package py.com.prestamos.response;

import lombok.Data;
import javax.xml.bind.annotation.*;
import java.util.List;
@Data
@XmlRootElement(name = "OPERACIONES")
@XmlAccessorType(XmlAccessType.FIELD)
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
