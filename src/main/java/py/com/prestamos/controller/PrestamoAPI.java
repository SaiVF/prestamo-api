package py.com.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import py.com.prestamos.constants.ApiConstants;
import py.com.prestamos.model.PrestamosPersona;
import py.com.prestamos.service.PrestamoService;

@RestController
@RequestMapping(value = "/")
public class PrestamoAPI {
    @Autowired
    PrestamoService prestamoService;

    @RequestMapping(value = ApiConstants.URL_MAPPING_CONSULTAR_PRESTAMOS, method = RequestMethod.GET)
    public PrestamosPersona consultarPrestamos(
            @RequestParam(value = ApiConstants.PARAM_MONEDA, required = true) Integer idMoneda,
            @RequestParam(value = ApiConstants.PARAM_NUMERO_DOC, required = false) String numeroDocumento,
            @RequestParam(value = ApiConstants.PARAM_PAIS_DOC, required = false) Integer paisDocumento,
            @RequestParam(value = ApiConstants.PARAM_TIPO_DOC, required = false) Integer tipoDocumento,
            @RequestParam(value = ApiConstants.PARAM_CUENTA, required = false) Integer cuenta) throws Exception {

        if (cuenta == null && (numeroDocumento == null || paisDocumento == null || tipoDocumento == null)) {
            throw new IllegalArgumentException("Se requieren parámetros válidos para la consulta.");
        }
        if (cuenta==null){
            return prestamoService.consultarPrestamoDocumento(paisDocumento, tipoDocumento, numeroDocumento, idMoneda);
        }
        return prestamoService.consultarPrestamoCuenta(cuenta, idMoneda);
    }

}
