package py.com.prestamos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoDetalleDTO {
    @XmlElement(name = "FSit")
    private Date fsit;

    @XmlElement(name = "CCuo")
    private Integer cantidadCuotas;

    @XmlElementWrapper(name = "Cuos")
    @XmlElement(name = "Cuo")
    List<PrestamoCuotaDTO> prestamoCuotas;

}
