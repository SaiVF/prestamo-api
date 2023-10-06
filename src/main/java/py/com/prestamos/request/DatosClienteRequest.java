package py.com.prestamos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class DatosClienteRequest {
    @NotNull
    @Schema(description = "Código Tipo Documento", example = "6900")
    private Integer moneda;

    @NotNull
    @Schema(description = "Numero de Documento", example = "123456")
    private String numeroDocumento;

    @NotNull
    @Schema(description = "Numero de cuenta del cliente", example = "1")
    private Integer paisDocumento;

    @NotNull
    @Schema(description = "Numero de cuenta del cliente", example = "1")
    private Integer tipoDocumento;

    @NotNull
    @Schema(description = "Tipo operacion", example = "12345")
    private Integer cuenta;

}
