package py.com.prestamos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoCuotaDTO {
    @XmlElement(name = "Prev")
    private PrestamoCuotaPrevDTO prev;
}
