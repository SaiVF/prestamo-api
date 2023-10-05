package py.com.prestamos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoCabeceraDTO {
    @XmlElement(name = "SIS")
    private Integer codigoProducto;

    @XmlElement(name = "NOM")
    private String nombreProducto;

    @XmlElement(name = "COP")
    private Integer cantidadOperacionesVigentes;
}
