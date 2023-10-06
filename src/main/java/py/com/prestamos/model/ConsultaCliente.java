package py.com.prestamos.model;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@XmlRootElement(name = "IN")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsultaCliente {
    @XmlElement(name = "PAIS")
    public Integer codigoPais;

    @XmlElement(name = "TDOC")
    public Integer codigoTipo;

    @XmlElement(name = "DOCUMENTO")
    public String nroDocumento;

    public String tipoOperacion;

    private Date fechaNacimiento;

    private String telefono;
}
