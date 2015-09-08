package manufacture.ws.DTO;

import java.util.ArrayList;
import java.util.List;

public class OrderResponseDTO {

	private String idUser ;							// donnees pour identifier le client
	private String IBAN ;							// donnees pour le paiement
	private String PaymentType ;					//différé ou immidia, pour le momentnous allos supposé que c'est différé
	private String datePaiement ;					// celle du différé ou du WS avec la banque si on ah=joute orchestrateur
	private String totalOrder ;						//prix totale de la commande
	private ArrayList<DevisResponseDTO> devisResponse ; 	// liste des produits
	
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	public String getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	public String getDatePaiement() {
		return datePaiement;
	}
	public void setDatePaiement(String datePaiement) {
		this.datePaiement = datePaiement;
	}
	public String getTotalOrder() {
		return totalOrder;
	}
	public void setTotalOrder(String totalOrder) {
		this.totalOrder = totalOrder;
	}
	public List<DevisResponseDTO> getDevisResponse() {
		return devisResponse;
	}
	public void setDevisResponse(ArrayList<DevisResponseDTO> devisResponse) {
		this.devisResponse = devisResponse;
	}
	
}
