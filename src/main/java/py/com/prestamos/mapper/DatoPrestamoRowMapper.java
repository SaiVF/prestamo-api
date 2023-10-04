package py.com.prestamos.mapper;

import org.springframework.jdbc.core.RowMapper;
import py.com.prestamos.model.DatoPrestamo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatoPrestamoRowMapper implements RowMapper<DatoPrestamo> {
    @Override
    public DatoPrestamo mapRow(ResultSet resultSet, int i) throws SQLException {
        DatoPrestamo datoPrestamo = new DatoPrestamo();
        datoPrestamo.setNroPrestamo(resultSet.getString("nro_prestamo"));
        datoPrestamo.setMoneda(resultSet.getString("moneda"));
        datoPrestamo.setNroDocumento(resultSet.getString("nro_documento"));
        datoPrestamo.setNombreCompleto(resultSet.getString("nombre_completo"));

        return datoPrestamo;
    }
}
