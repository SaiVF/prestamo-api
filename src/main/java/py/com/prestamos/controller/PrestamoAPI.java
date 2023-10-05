package py.com.prestamos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import py.com.prestamos.model.PrestamosPersona;
import py.com.prestamos.request.DatosClienteRequest;
import py.com.prestamos.service.PrestamoService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.SQLDataException;

@Tag(name = "prestamo")
@RestController
@RequestMapping(value = "/")
public class PrestamoAPI {
    @Autowired
    PrestamoService prestamoService;
    @RequestMapping(value = "/consultar-prestamos", method = RequestMethod.GET)
    public PrestamosPersona consultarPrestamos(
            @RequestParam(name = "moneda") @NotNull Integer moneda,
            @RequestParam(name = "numeroDocumento") @NotNull String numeroDocumento,
            @RequestParam(name = "paisDocumento") @NotNull Integer paisDocumento,
            @RequestParam(name = "tipoDocumento") @NotNull Integer tipoDocumento,
            @RequestParam(name = "cuenta") @NotNull Integer cuenta) throws Exception {
        DatosClienteRequest request = prestamoService.getParams(moneda, numeroDocumento, paisDocumento,tipoDocumento,cuenta);
        PrestamosPersona response = prestamoService.consultarPrestamo(request);
        return response;
    }
    }


