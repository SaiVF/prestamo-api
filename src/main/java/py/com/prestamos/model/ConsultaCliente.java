package py.com.prestamos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
