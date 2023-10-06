package py.com.prestamos.response;

import lombok.Data;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.List;
@Data
public class PrestamoDetalleDTO {
    @XmlElement(name = "FSit")
    private Date fsit;

    @XmlElement(name = "CCuo")
    private Integer cantidadCuotas;
    
    @XmlElement(name = "Cuo")
    List<PrestamoCuotaDTO> prestamoCuotas;

}
