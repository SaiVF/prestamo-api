package py.com.prestamos.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
@Data
public class PrestamoCuotaDTO {
    @XmlElement(name = "Prev")
    private PrestamoCuotaPrevDTO prev;
}
