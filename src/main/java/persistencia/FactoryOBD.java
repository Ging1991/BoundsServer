package persistencia;

import persistencia.interfaz.FrecuenciaOBD;
import persistencia.postgresql.FrecuenciaOBDPostgresql;

public class FactoryOBD {
	
	public static FrecuenciaOBD crearFrecuenciaOBD() {
		return new FrecuenciaOBDPostgresql();
	}

}