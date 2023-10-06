package py.com.prestamos.request;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
@Data
public class ConsultaPorDocumentoRequest {

    @XmlElement(name = "Pais")
    private Integer codigoPais;

    @XmlElement(name = "TDoc")
    private Integer codigoTipoDoc;

    @XmlElement(name = "NroDoc")
    private String numeroDocumento;
}
