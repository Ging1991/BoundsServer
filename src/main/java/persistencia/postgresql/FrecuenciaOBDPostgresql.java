package persistencia.postgresql;

import java.sql.ResultSet;
import java.sql.SQLException;

import infraestructura.OBD;
import persistencia.entidad.Frecuencia;
import persistencia.interfaz.FrecuenciaOBD;

public class FrecuenciaOBDPostgresql extends OBD<Frecuencia> implements FrecuenciaOBD {

	// Inicializa el OBD
	public FrecuenciaOBDPostgresql() {
		tabla = "frecuencias";
		campos = "cantidad";
	}
	
	public void insert(Frecuencia frecuencia) {
		String comandoSQL = "INSERT INTO " + tabla + "(id, " + campos + ") "
				+ "VALUES (" + frecuencia.getId() +","+frecuencia.getCantidad()+");";
		ejecutarSQL(comandoSQL);
	}

	public void update(Frecuencia frecuencia) {
		String condicion = "ID = " + frecuencia.getId();
		String valores = "cantidad = "+ frecuencia.getCantidad();
		String consulta = "update " + tabla + " set " + valores + "  where ("+condicion+");";
		ejecutarSQL(consulta);
	}


	public Frecuencia generar(ResultSet resultados) {
		Frecuencia ret = null;
		try {
			
			ret = new Frecuencia(
				resultados.getInt("ID"),
				resultados.getInt("cantidad")
			);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	protected Integer getID(Frecuencia e) {
		return e.getId();
	}

}