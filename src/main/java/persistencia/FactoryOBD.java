package persistencia;

import persistencia.interfaz.FrecuenciaOBD;
import persistencia.postgresql.FrecuenciaOBDPostgresql;

public class FactoryOBD {//asas
	
	public static FrecuenciaOBD crearFrecuenciaOBD() {
		return new FrecuenciaOBDPostgresql();
	}

}