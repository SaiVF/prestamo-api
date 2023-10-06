package py.com.prestamos.model;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@XmlRootElement(name = "DATOS")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosCliente {
    @XmlElement(name = "PNOMBRE")
    private String primerNombre;

    @XmlElement(name = "SNOMBRE")
    private String segundoNombre;

    @XmlElement(name = "PAPELLIDO")
    private String primerApellido;

    @XmlElement(name = "SAPELLIDO")
    private String segundoApellido;

    @XmlElement(name = "SEXO")
    private String sexo;

    @XmlElement(name = "PAISNACIMIENTO")
    private Integer paisNacimiento;

    @XmlElement(name = "NACIONALIDAD")
    private Integer paisNacionalidad;

    @XmlElement(name = "FNACIMIENTO")
    private Date fechaNacimiento;

    @XmlElement(name = "FVENCDOC")
    private Date fechaVencimiento;

    @XmlElement(name = "TELEFONO")
    private String telefono;

    @XmlElement(name = "CELULAR")
    private String nroCelular;

    @XmlElement(name = "EMAIL")
    private String email;

    @XmlElement(name = "DIRECCION")
    private String direccion;

    @XmlElement(name = "PROFESION")
    private Integer codProfesion;

    @XmlElement(name = "OCUPACION")
    private Integer codOcupacion;

    private String codPersona;

    private String descProfesion;

    private String descProfesionIng;

    private int codError;

    private String mensaje;
}
