package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OBD {
	protected String driver = "org.postgresql.Driver";
	
	// local asas sdsd
//	protected String cadenaConexion = "jdbc:postgresql://localhost:5432/NTK_NOTITEK"; 
//	protected String usuarioBD = "postgres"; 
//	protected String passwordBD = "root";asas
		
	//produccion
	protected String cadenaConexion = "jdbc:postgresql://ec2-3-215-57-87.compute-1.amazonaws.com:5432/dapafptvgi6hbl?sslmode=require";
	protected String usuarioBD = "wqsxovprvndbsq"; 
	protected String passwordBD = "793bf52f8c2de23032241b0f7f32365cfa1fc1d885c91cd8c68fb94584fe4097";
	
	public void ejecutarComandoSQL(String sql) {
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD);
			Statement sentencia = conexion.createStatement ();
			sentencia.executeUpdate(sql);		
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println("       ERROR: "+sql);
			e.printStackTrace();
		}
		
	}
		
}