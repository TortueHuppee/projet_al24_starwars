package manufacture.ws.DTO;

import java.util.ArrayList;
import java.util.List;

public class OrderResponseDTO {

//	private String idUser ;							// donnees pour identifier le client
//	private String IBAN ;							// donnees pour le paiement
	private String PaymentType ;					//diff�r� ou immidia, pour le momentnous allos suppos� que c'est diff�r�
	private String datePaiement ;					// celle du diff�r� ou du WS avec la banque si on ah=joute orchestrateur
	private ArrayList<DevisResponseDTO> ListProductToOrder ; 	// liste des produits
	
	
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
	public ArrayList<DevisResponseDTO> getListProductToOrder() {
		return ListProductToOrder;
	}
	public void setListProductToOrder(ArrayList<DevisResponseDTO> listProductToOrder) {
		ListProductToOrder = listProductToOrder;
	}
}
