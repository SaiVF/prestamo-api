package py.com.prestamos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaPorDocumentoRequest {

    @XmlElement(name = "Pais")
    private Integer codigoPais;

    @XmlElement(name = "TDoc")
    private Integer codigoTipoDoc;

    @XmlElement(name = "NroDoc")
    private String numeroDocumento;
}
