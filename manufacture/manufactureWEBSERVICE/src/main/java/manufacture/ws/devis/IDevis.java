package manufacture.ws.devis;

import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IDevis {
	
	public ArrayList<DevisResponseDTO> getDevis(
			@WebParam(name = "devisRequest")ArrayList<DevisRequestDTO> devisRequest);
	
}
