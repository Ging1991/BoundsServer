package servicios;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import negocios.FrecuenciaManager;
import servicios.datos.DatoMazo;

@Path("/mazos/servicios")
public class Servicios {

	@GET
	@Path("/get_ejemplo")
	@Produces(MediaType.APPLICATION_JSON)
	public DatoMazo getEjemplo(){
		List<Integer> cartas = new ArrayList<Integer>();
		cartas.add(1);
		cartas.add(2);
		cartas.add(3);
		cartas.add(4);
		cartas.add(5);
		DatoMazo mazo = new DatoMazo(cartas);
		return mazo;
	}
	
	@POST
	@Path("/post_ejemplo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postEjemplo(DatoMazo mazo){
		String mensaje = "Ingreso: "+ mazo;
		return Response.status(201).entity(mensaje).build();
	}

	
	@POST
	@Path("/guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DatoMazo guarda(DatoMazo mazo){
		FrecuenciaManager.guardarFrecuencias(mazo);
		return mazo;
	}

}