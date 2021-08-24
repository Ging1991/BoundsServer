package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import persistencia.interfaz.Generardor;

public class OBD <entidad> {
	protected String driver = "org.postgresql.Driver";
	
	// Local
//	protected String cadenaConexion = "jdbc:postgresql://localhost:5432/NTK_NOTITEK"; 
//	protected String usuarioBD = "postgres"; 
//	protected String passwordBD = "root";
		
	//produccion
	protected String cadenaConexion = "jdbc:postgresql://ec2-3-215-57-87.compute-1.amazonaws.com:5432/dapafptvgi6hbl?sslmode=require";
	protected String usuarioBD = "wqsxovprvndbsq"; 
	protected String passwordBD = "793bf52f8c2de23032241b0f7f32365cfa1fc1d885c91cd8c68fb94584fe4097";
	
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

	public List<entidad> select(String SQL, Generardor<entidad> generador) {
		List<entidad> ret = new ArrayList<entidad>();
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(SQL);			

			while (resultados.next())
				ret.add(generador.generar(resultados));

			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println("       ERROR: " + SQL);
			e.printStackTrace();
		}
			
		return ret;		
	}
			
}