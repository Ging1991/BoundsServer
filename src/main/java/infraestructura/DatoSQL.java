package infraestructura;

public class DatoSQL {
	private String nombre;
	private Integer datoEntero;
	private String datoCadena;
	private TipoDato tipo;
	
	public DatoSQL(String nombre, Integer datoEntero) {
		this.nombre = nombre;
		this.datoEntero = datoEntero;
		this.tipo = TipoDato.ENTERO;
	}

	public DatoSQL(String nombre, String datoCadena) {
		this.nombre = nombre;
		this.datoCadena = datoCadena;
		this.tipo = TipoDato.CADENA;
	}
	
	// Devuelve el dato como cadena para una consulta SQL
	// Para las cadenas se devuelve entre ' '
	public String getDato() {
		if (tipo == TipoDato.CADENA)
			return String.format("'%s'", datoCadena);
		if (tipo == TipoDato.ENTERO)
			return datoEntero.toString();
		return "";	
	}

	public String getNombre() {
		return nombre;
	}
	
}