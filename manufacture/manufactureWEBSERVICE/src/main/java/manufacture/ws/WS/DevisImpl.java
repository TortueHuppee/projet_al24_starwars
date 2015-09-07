package manufacture.ws.WS;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.jws.WebService;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import manufacture.entity.product.Product;
import manufacture.ifacade.catalog.ICatalog;
import manufacture.ws.DTO.DevisRequestDTO;
import manufacture.ws.DTO.DevisResponseDTO;

@Transactional
@Component
@WebService(endpointInterface="manufacture.ws.WS.IDevis")
public class DevisImpl implements IDevis {

	private static Logger log = Logger.getLogger(DevisImpl.class);
	
	private static final String AVAILABILITY_STATE_RESTOCKING = "En cours de réapprovisionnement...";
	private static final String AVAILABILITY_STATE_AVAILABLE = "Disponible";
	private static final String AVAILABILITY_STATE_NOT_AVAILABLE = "Non disponible";
	private static final String AVAILABILITY_STATE_STOCK_INSUFFICIENT = "stock insuffisant, quantité ajustée";	

//    @ManagedProperty(value="#{catalog}")
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
		List<Product> listeProduct = proxyCatalog.getAllProductByProductRef(Integer.parseInt(devisRequest.getIdProductRef()));
		DevisResponseDTO devisResponse = new DevisResponseDTO();
		devisResponse = convertRequestDTO(devisRequest);
		
		if(listeProduct.isEmpty()){
			devisResponse.setQuantity("0");
			devisResponse.setPrice("0");
			devisResponse.setAvailability(AVAILABILITY_STATE_NOT_AVAILABLE);
			
		} else {
			devisResponse.setQuantity("0");
			devisResponse.setPrice("0");
			devisResponse.setAvailability(AVAILABILITY_STATE_NOT_AVAILABLE);
			for (Product product : listeProduct) {
				boolean isTheSame = true ;
				if (product.getColor().getIdColor() != Integer.parseInt(devisRequest.getIdColor())) {
					isTheSame = false ;
				}
				if (product.getMaterial().getIdMaterial() != Integer.parseInt(devisRequest.getIdMaterial())) {
					isTheSame = false ;
				}
				if (product.getConstructor().getIdConstructor() != Integer.parseInt(devisRequest.getIdConstructor())) {
					isTheSame = false ;
				}
				if (isTheSame){
					if (product.getStock() == 0) {
						devisResponse.setQuantity("0");
						devisResponse.setPrice("0");
						devisResponse.setAvailability(AVAILABILITY_STATE_NOT_AVAILABLE);
					} else {
						if (product.getStock()>=Integer.parseInt(devisRequest.getQuantity())) {
							devisResponse.setPrice(""+product.getPrice());
							devisResponse.setAvailability(AVAILABILITY_STATE_AVAILABLE);
						} else {
							devisResponse.setPrice(""+product.getPrice());
							devisResponse.setQuantity(""+product.getStock());
							devisResponse.setAvailability(AVAILABILITY_STATE_STOCK_INSUFFICIENT);
						}
					}
				}
			}
		}
		
		
		return devisResponse ;
	}
	
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
