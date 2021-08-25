package infraestructura;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
	private static final String direccion = "config.properties";
	
	public static String recuperar(String clave) {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		String ret = "";
		
		try {	
			entrada = new FileInputStream(direccion);
			propiedades.load(entrada);
			ret = propiedades.getProperty(clave);
	
		} catch (IOException ex) {
			ex.printStackTrace();
			
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		return ret;
	}
	
	public static void guardar(String clave, String valor) {
		
		Properties propiedades = new Properties();
		
		// Cargo los datos existentes en propiedades
		FileInputStream entrada = null;
		try {
			entrada = new FileInputStream(direccion);
			propiedades.load(entrada);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
		
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Coloco el nuevo valor y lo guardo
		FileOutputStream salida = null;
		
		try {
			salida = new FileOutputStream(direccion);
			propiedades.setProperty(clave, valor);
			propiedades.store(salida, null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			if (salida != null) {
				try {
					salida.close();
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}
	
	public static void main(String[] args) {
		
		// Ejecutar para guardar los valores por primera vez o cambiarlos
		guardar("cadenaConexion", "TuCadena");
		guardar("usuarioBD", "TuUsuario");
		guardar("passwordBD", "TuPass");
		guardar("driver", "org.postgresql.Driver");
		
		System.out.println("Cadena conexion: " + recuperar("cadenaConexion"));
		System.out.println("Usuario BD: " + recuperar("usuarioBD"));
		System.out.println("Password BD: " + recuperar("passwordBD"));
		System.out.println("Driver : " + recuperar("driver"));
	}

}