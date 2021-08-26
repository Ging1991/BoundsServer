package persistencia.postgresql;

import java.sql.ResultSet;
import java.sql.SQLException;

import infraestructura.GeneradorSQL;
import infraestructura.OBD;
import persistencia.entidad.Frecuencia;
import persistencia.interfaz.FrecuenciaOBD;

public class FrecuenciaOBDPostgresql extends OBD<Frecuencia> implements FrecuenciaOBD {

	// Inicializa el OBD
	public FrecuenciaOBDPostgresql() {
		tabla = "frecuencias";
		campos = "cantidad";
	}
	
	public void insertConID(Frecuencia frecuencia) {
		String comandoSQL = "INSERT INTO " + tabla + "(id, " + campos + ") "
				+ "VALUES (" + frecuencia.getId() +","+frecuencia.getCantidad()+");";
		ejecutarSQL(comandoSQL);
	}

	public Frecuencia generar(ResultSet resultados) throws SQLException {
		return new Frecuencia(
				resultados.getInt("ID"),
				resultados.getInt("cantidad")
			);
	}

	@Override
	public GeneradorSQL getGenerador(Frecuencia e) {
		GeneradorSQL ret = new GeneradorSQL(tabla);
		ret.agregar("ID", e.getId());
		ret.agregar("cantidad", e.getCantidad());
		return ret;
	}

}