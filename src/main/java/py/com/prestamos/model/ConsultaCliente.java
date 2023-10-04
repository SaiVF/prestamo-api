package py.com.prestamos.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.Date;

@Data
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
