package py.com.prestamos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import py.com.prestamos.constants.ApiConstants;
import py.com.prestamos.model.PrestamosPersona;
import py.com.prestamos.request.DatosClienteRequest;
import py.com.prestamos.service.PrestamoService;

import javax.validation.Valid;
import java.sql.SQLDataException;

@Tag(name = "prestamo")
@RestController
@RequestMapping(value = "/")
public class PrestamoAPI {
    @Autowired
    PrestamoService prestamoService;
    @RequestMapping(value = "/consultar-prestamos", method = RequestMethod.GET)
    public PrestamosPersona consultarPrestamos(
            @RequestParam(value = ApiConstants.PARAM_MONEDA, required = true) Integer moneda,
            @RequestParam(value = ApiConstants.PARAM_NUMERO_DOC, required = false) String numeroDocumento,
            @RequestParam(value = ApiConstants.PARAM_PAIS_DOC, required = false) Integer paisDocumento,
            @RequestParam(value = ApiConstants.PARAM_TIPO_DOC, required = false) Integer tipoDocumento,
            @RequestParam(value = ApiConstants.PARAM_CUENTA, required = false) Integer cuenta) throws Exception {
        if (cuenta==null){
            return prestamoService.consultarPrestamoDocumento(paisDocumento, tipoDocumento, numeroDocumento, moneda);
        }
        return prestamoService.consultarPrestamoCuenta(cuenta, moneda);
    }

}
