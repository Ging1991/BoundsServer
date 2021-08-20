package persistencia.entidad;

public class Frecuencia {
	private Integer id, cantidad;

	public Frecuencia(Integer id, Integer cantidad) {
		this.id = id;
		this.cantidad = cantidad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}