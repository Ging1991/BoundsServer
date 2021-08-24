package persistencia.interfaz;

import persistencia.entidad.Frecuencia;

public interface FrecuenciaOBD {
	
	public void insert(Frecuencia frecuencia);

	public void update(Frecuencia frecuencia);
	
	public void delete(Frecuencia frecuencia);

	public Frecuencia selectByID(Integer ID);
	
}