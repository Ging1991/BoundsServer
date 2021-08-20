package servicios.datos;

import java.util.List;

public class DatoMazo {
	
	private List<Integer> cartas;

	public DatoMazo() {}

	public DatoMazo(List<Integer> cartas) {
		this.cartas = cartas;
	}

	public List<Integer> getCartas() {
		return cartas;
	}

	public void setCartas(List<Integer> cartas) {
		this.cartas = cartas;
	}

}