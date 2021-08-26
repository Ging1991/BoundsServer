package persistencia.interfaz;

import persistencia.entidad.Frecuencia;

public interface ConsultaOBD<Entidad> {
	
	public void insert(Entidad entidad);

	public void update(Entidad entidad);
	
	public void delete(Entidad entidad);

	public Frecuencia selectByID(Integer ID);
	
}