package persistencia.interfaz;

import java.sql.ResultSet;

// Los OBD lo implementan para poder generar una entidad
public interface Generardor<entidad> {
	
	// Devuelve un unico elemento de clase [entidad] extraido de los resultados
	public entidad generar(ResultSet resultados);

}