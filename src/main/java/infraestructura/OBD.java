package infraestructura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class OBD <entidad> {
	public String tabla;
	public String campos;
	protected static String driver;
	protected static String cadenaConexion;
	protected static String usuarioBD; 
	protected static String passwordBD;

	static {
		cadenaConexion = Configuracion.recuperar("cadenaConexion");
		usuarioBD = Configuracion.recuperar("usuarioBD");
		passwordBD = Configuracion.recuperar("passwordBD");
		driver = Configuracion.recuperar("driver");
	}
		
	public void ejecutarSQL(String SQL) {
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD);
			Statement sentencia = conexion.createStatement ();
			sentencia.executeUpdate(SQL);		
			sentencia.close();
			conexion.close();
			
		} catch(Exception e) {
			System.out.println("       ERROR: " + SQL);
			e.printStackTrace();
		}
		
	}

	public List<entidad> select(String SQL) {
		List<entidad> ret = new ArrayList<entidad>();
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(SQL);			

			while (resultados.next())
				ret.add(generar(resultados));

			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println("       ERROR: " + SQL);
			e.printStackTrace();
		}
			
		return ret;		
	}

	// Devuelve un unico elemento de clase [entidad] extraido de los resultados
	public abstract entidad generar(ResultSet resultados) throws SQLException;
	

	protected abstract GeneradorSQL getGenerador(entidad e);

	// Metodos SQL estandar
	public void insert(entidad e) {
		GeneradorSQL sql = getGenerador(e);
		String consulta = sql.generarInsert();
		ejecutarSQL(consulta);
	}

	public void update(entidad e) {
		GeneradorSQL sql = getGenerador(e);
		String consulta = sql.generarUpdate();
		ejecutarSQL(consulta);
	}

	// Metodos SQL estandar
	public void delete(entidad e) {
		GeneradorSQL sql = getGenerador(e);
		String consulta = sql.generarDelete();
		ejecutarSQL(consulta);
	}
		
	public List<entidad> selectByCondicion(String condicion) {
		String comandoSQL = "select ID, " + campos + " from " + tabla + " where ("+condicion+");";
		return select(comandoSQL);
	}
	
	public entidad selectUnicoByCondicion(String condicion) {
		List<entidad> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}
	
	public entidad selectByID(Integer ID) {
		String condicion = "ID = " + ID;
		return selectUnicoByCondicion(condicion);
	}
	
}