package py.com.prestamos.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PrestamoOperacionDTO {
    @XmlElement(name = "NRO")
    private Long numeroOperacion;

    @XmlElement(name = "SBO")
    private Integer subOperacion;

    @XmlElement(name = "TOP")
    private Integer tipoOperacion;

    @XmlElement(name = "MDA")
    private Integer moneda;

    @XmlElement(name = "MON")
    private String monedaLetras;

    @XmlElement(name = "MOD")
    private Integer modulo;

    @XmlElement(name = "PAP")
    private Integer papel;

    @XmlElement(name = "SUC")
    private Integer sucursal;

    @XmlElement(name = "FVA")
    private Date fechaDesembolso;

    @XmlElement(name = "FVT")
    private Date fechaVencimiento;

    @XmlElement(name = "PZO")
    private Integer plazoDias;

    @XmlElement(name = "TAS")
    private BigDecimal tasa;

    @XmlElement(name = "TTA")
    private Integer tipoTasa;

    @XmlElement(name = "tipoTasaDescripcion")
    private String ttd;

    @XmlElement(name = "AON")
    private BigDecimal interesAlVencimiento;

    @XmlElement(name = "SIG")
    private String signoMoneda;

    @XmlElement(name = "SDO")
    private BigDecimal saldo;

    @XmlElement(name = "EBC")
    private String comunal;

    @XmlTransient
    private PrestamoDetalleDTO detalle;
}
