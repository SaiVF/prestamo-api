package py.com.prestamos.response;

import lombok.Data;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class PrestamoCuotaPrevDTO {

    @XmlElement(name = "Nro")
    private Integer numeroCuota;

    @XmlElement(name = "FPg")
    private Date fechaVencimiento;

    @XmlElement(name = "Imp")
    private BigDecimal importeAPagar;

    @XmlElement(name = "Con")
    private Integer con;

    @XmlElement(name = "Est")
    private Integer est;

    @XmlElement(name = "Cap")
    private Integer cap;

    @XmlElement(name = "Ints")
    private Integer ints;

    @XmlElement(name = "Mor")
    private Integer mor;

    @XmlElement(name = "Tax")
    private Integer tax;

    @XmlElement(name = "Seg")
    private Integer seg;

    @XmlElement(name = "Sta")
    private Integer sta;
}
