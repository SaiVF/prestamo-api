package py.com.prestamos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public PrestamosPersona consultarPrestamos(@Valid @RequestBody DatosClienteRequest request) throws Exception {
    PrestamosPersona response = prestamoService.consultarPrestamo(request);
    return response;
    }

}
