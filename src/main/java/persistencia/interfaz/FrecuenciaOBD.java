package persistencia.interfaz;

import persistencia.entidad.Frecuencia;

public interface FrecuenciaOBD extends ConsultaOBD<Frecuencia>{
	
	public void insertConID(Frecuencia frecuencia);
	
}