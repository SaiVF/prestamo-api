package py.com.prestamos.response;

import lombok.Data;
import javax.xml.bind.annotation.XmlElement;

@Data
public class PrestamoCabeceraDTO {
    @XmlElement(name = "SIS")
    private Integer codigoProducto;

    @XmlElement(name = "NOM")
    private String nombreProducto;

    @XmlElement(name = "COP")
    private Integer cantidadOperacionesVigentes;
}
