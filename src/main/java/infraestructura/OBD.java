package infraestructura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class OBD <entidad> {
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
	public abstract entidad generar(ResultSet resultados);
			
}