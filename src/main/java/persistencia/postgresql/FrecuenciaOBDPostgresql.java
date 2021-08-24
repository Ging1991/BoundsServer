package persistencia.postgresql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import persistencia.OBD;
import persistencia.entidad.Frecuencia;
import persistencia.interfaz.FrecuenciaOBD;
import persistencia.interfaz.Generardor;

public class FrecuenciaOBDPostgresql extends OBD<Frecuencia> implements FrecuenciaOBD, Generardor<Frecuencia> {
	public String tabla = "frecuencias";
	public String campos = "cantidad";
	
	public void insert(Frecuencia frecuencia) {
		String comandoSQL = "INSERT INTO " + tabla + "(id, " + campos + ") "
				+ "VALUES (" + frecuencia.getId() +","+frecuencia.getCantidad()+");";
		ejecutarSQL(comandoSQL);
	}

	public void update(Frecuencia frecuencia) {
		String condicion = "ID = "+frecuencia.getId();
		String valores = "cantidad = "+ frecuencia.getCantidad();
		String consulta = "update " + tabla + " set " + valores + "  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public void delete(Frecuencia frecuencia) {
		String condicion = "ID = " + frecuencia.getId();
		String consulta = "delete from " + tabla + " where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public Frecuencia selectByID(Integer ID) {
		String condicion = "ID = " + ID;
		return selectUnicoByCondicion(condicion);
	}

	private Frecuencia selectUnicoByCondicion(String condicion) {
		List<Frecuencia> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	private List<Frecuencia> selectByCondicion(String condicion) {
		String comandoSQL = "select ID, " + campos + " from " + tabla + " where ("+condicion+");";
		return select(comandoSQL, this);
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

}