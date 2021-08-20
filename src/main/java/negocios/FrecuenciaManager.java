package negocios;

import persistencia.FactoryOBD;
import persistencia.entidad.Frecuencia;
import persistencia.interfaz.FrecuenciaOBD;
import servicios.datos.DatoMazo;

public class FrecuenciaManager {
	
	public static void guardarFrecuencias(DatoMazo dato) {
		for (Integer cartaID : dato.getCartas())
			guardarCarta(cartaID);
	}
	
	public static void guardarCarta(Integer cartaID) {
		FrecuenciaOBD obd = FactoryOBD.crearFrecuenciaOBD();
		Frecuencia frecuencia = obd.selectByID(cartaID);
		
		// Si no existia, la creo
		if (frecuencia == null) {
			obd.insert(new Frecuencia(cartaID, 0));
			frecuencia = obd.selectByID(cartaID);
		}
		
		// Incremento la cantidad
		frecuencia.setCantidad(frecuencia.getCantidad()+1);
		obd.update(frecuencia);
	}
	
}