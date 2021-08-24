package persistencia.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import persistencia.OBD;
import persistencia.entidad.Frecuencia;
import persistencia.interfaz.FrecuenciaOBD;
import persistencia.interfaz.GenerarEntidad;

public class FrecuenciaOBDPostgresql extends OBD implements FrecuenciaOBD, GenerarEntidad {
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
	
	@SuppressWarnings("unchecked")
	private List<Frecuencia> selectByCondicion1(String condicion) {
		String comandoSQL = "select ID, " + campos + " from " + tabla + " where ("+condicion+");";
		Object ret = ejecutarComandoSQL(comandoSQL, this);
			
		return (List<Frecuencia>) ret;
	}

	public Object generar(ResultSet resultados) {
		List<Frecuencia> ret = new ArrayList<Frecuencia>();
		try {
			while (resultados.next()) {
				ret.add(new Frecuencia(
						resultados.getInt("ID"),
						resultados.getInt("cantidad")
					));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}