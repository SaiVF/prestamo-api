package py.com.prestamos.mapper;

import org.springframework.jdbc.core.RowMapper;
import py.com.prestamos.model.PrestamoCuota;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrestamoCuotaRowMapper implements RowMapper<PrestamoCuota> {
    @Override
    public PrestamoCuota mapRow(ResultSet resultSet, int i) throws SQLException {
        PrestamoCuota prestamoCuota = new PrestamoCuota();
        prestamoCuota.setNroCuota(resultSet.getInt("cuota"));
        prestamoCuota.setFecVencimiento(resultSet.getTimestamp("fec_vto"));
        prestamoCuota.setMontoPagar(resultSet.getBigDecimal("monto_pagar"));
        return prestamoCuota;
    }
}
