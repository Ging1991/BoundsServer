package persistencia.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import persistencia.OBD;
import persistencia.entidad.Frecuencia;
import persistencia.interfaz.FrecuenciaOBD;

public class FrecuenciaOBDPostgresql extends OBD implements FrecuenciaOBD {
	public String tabla = "frecuencias";
	public String campos = "cantidad";
	
	public void insert(Frecuencia frecuencia) {
		String comandoSQL = "INSERT INTO " + tabla + "(id, " + campos + ") "
				+ "VALUES (" + frecuencia.getId() +","+frecuencia.getCantidad()+");";
		ejecutarComandoSQL(comandoSQL);
	}

	public void update(Frecuencia frecuencia) {
		String condicion = "ID = "+frecuencia.getId();
		String valores = "cantidad = "+ frecuencia.getCantidad();
		String consulta = "update " + tabla + " set "+valores+"  where ("+condicion+");";
		ejecutarComandoSQL(consulta);
	}

	public void delete(Frecuencia frecuencia) {
		String condicion = "ID = " + frecuencia.getId();
		String consulta = "delete from " + tabla + " where ("+condicion+");";
		ejecutarComandoSQL(consulta);
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
		List<Frecuencia> ret = new ArrayList<Frecuencia>();
		String comandoSQL = "select ID, " + campos + " from " + tabla + " where ("+condicion+");";
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Frecuencia(
						resultados.getInt("ID"),
						resultados.getInt("cantidad")
					));
			}

			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return ret;
	}

}