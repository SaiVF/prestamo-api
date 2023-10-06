package py.com.prestamos.service;

import py.com.prestamos.model.PrestamosPersona;
import py.com.prestamos.request.DatosClienteRequest;

import java.sql.SQLDataException;

public interface PrestamoService {
    PrestamosPersona consultarPrestamoDocumento(Integer paisDocumento, Integer tipoDocumento, String numeroDocumento, Integer moneda) throws Exception;

    PrestamosPersona consultarPrestamoCuenta(Integer cuenta, Integer moneda) throws Exception;
}
