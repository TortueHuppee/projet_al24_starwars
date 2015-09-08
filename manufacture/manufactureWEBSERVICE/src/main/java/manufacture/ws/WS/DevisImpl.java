package manufacture.ws.WS;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.transaction.Transactional;

import manufacture.entity.product.Product;
import manufacture.ifacade.catalog.ICatalog;
import manufacture.ws.DTO.DevisRequestDTO;
import manufacture.ws.DTO.DevisResponseDTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Transactional
@Component
@WebService(endpointInterface="manufacture.ws.WS.IDevis")
public class DevisImpl implements IDevis {

	private static Logger log = Logger.getLogger(DevisImpl.class);
	
	private static final String AVAILABILITY_STATE_RESTOCKING = "En cours de réapprovisionnement...";
	private static final String AVAILABILITY_STATE_AVAILABLE = "Disponible";
	private static final String AVAILABILITY_STATE_NOT_AVAILABLE = "Non disponible";
	private static final String AVAILABILITY_STATE_STOCK_INSUFFICIENT = "stock insuffisant, quantité ajustée";	

    private ICatalog proxyCatalog;
    
	@Override
	public ArrayList<DevisResponseDTO> getDevis(ArrayList<DevisRequestDTO> devisRequestList) {
		
		ArrayList<DevisResponseDTO> listeResponse = new ArrayList<>();
		for (DevisRequestDTO devisRequest : devisRequestList) {
			listeResponse.add(checkStockByIdProduct(devisRequest));
		}
		
		return listeResponse ;
	}
	
	private DevisResponseDTO checkStockByIdProduct(DevisRequestDTO devisRequest)
	{
		log.info("devis resquest id product ref : " + devisRequest.getIdProductRef());
		
		List<Product> listeProduct = proxyCatalog.getAllProductByProductRef(devisRequest.getIdProductRef());
		
		DevisResponseDTO devisResponse = new DevisResponseDTO();
		devisResponse = convertRequestDTO(devisRequest);
		
		//Cas 1 : la reference produit demandee n'est pas enregistree dans la base de donnees
		if(listeProduct.isEmpty())
		{
			devisResponse.setQuantity(0);
			devisResponse.setPrice(0.0);
			devisResponse.setAvailability(AVAILABILITY_STATE_NOT_AVAILABLE);
			
		} 
		else
		{
			devisResponse.setQuantity(0);
			devisResponse.setPrice(0.0);
			devisResponse.setAvailability(AVAILABILITY_STATE_NOT_AVAILABLE);
			
			for (Product product : listeProduct) 
			{
				boolean isTheSame = true ;
				
				if (product.getColor().getIdColor() != devisRequest.getIdColor())
				{
					isTheSame = false ;
				}
				
				if (product.getMaterial().getIdMaterial() != devisRequest.getIdMaterial())
				{
					isTheSame = false ;
				}
				
				if (product.getConstructor().getIdConstructor() != devisRequest.getIdConstructor())
				{
					isTheSame = false ;
				}
				
				if (isTheSame)
				{
					//Cas 2 : le produit est enregistre dans la base de donnees mais le stock est null
					if (product.getStock() == 0) 
					{
						devisResponse.setQuantity(0);
						devisResponse.setPrice(0.0);
						devisResponse.setAvailability(AVAILABILITY_STATE_NOT_AVAILABLE);
					} 
					else 
					{
						//Cas 3 : le produit est enregistre dans la base de donnees et le stock est suffisant
						if (product.getStock() >= devisRequest.getQuantity())
						{
							devisResponse.setPrice(product.getPrice());
							devisResponse.setQuantity(devisRequest.getQuantity());
							devisResponse.setAvailability(AVAILABILITY_STATE_AVAILABLE);
						} 
						//Cas 4 : le produit est enregistre dans la base de donnees mais le stock est insuffisant
						else 
						{
							devisResponse.setPrice(product.getPrice());
							devisResponse.setQuantity(product.getStock());
							devisResponse.setAvailability(AVAILABILITY_STATE_STOCK_INSUFFICIENT);
						}
					}
				}
			}
		}
		return devisResponse ;
	}
	
	/**
	 * Convertir un objet de classe DevisRequestDTO en un objet de classe DevisResponseDTO.
	 * @param devisRequest l'objet de classe DevisRequestDTO qui sera convertit.
	 * @return devisResponse l'objet devisRequest convertit en objet de classe DevisResponseDTO.
	 */
	private DevisResponseDTO convertRequestDTO (DevisRequestDTO devisRequest){
		
		DevisResponseDTO devisResponse = new DevisResponseDTO();
		
		devisResponse.setIdProductRef(devisRequest.getIdProductRef());
		devisResponse.setIdColor(devisRequest.getIdColor());
		devisResponse.setIdConstructor(devisRequest.getIdConstructor());
		devisResponse.setIdMaterial(devisRequest.getIdMaterial());
		devisResponse.setQuantity(devisRequest.getQuantity());
		
		return devisResponse;
	}

	public ICatalog getProxyCatalog() {
		return proxyCatalog;
	}

	@Autowired
	public void setProxyCatalog(ICatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}

}
