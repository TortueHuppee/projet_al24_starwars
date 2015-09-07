package manufacture.ws.WS;

import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebService;

import manufacture.ws.DTO.DevisRequestDTO;
import manufacture.ws.DTO.DevisResponseDTO;

@WebService
public interface IDevis {
	
	public ArrayList<DevisResponseDTO> getDevis(
			@WebParam(name = "devisRequest")ArrayList<DevisRequestDTO> devisRequest);
	
}
