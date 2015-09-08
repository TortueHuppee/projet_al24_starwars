package manufacture.ws.DTO;

import java.util.ArrayList;

public class OrderRequestDTO {
	
	private String emailUser ;		// donnees pour identifier le client
	private String IBAN ;		// donnees pour le paiement
	private String PaymentType ;
	private ArrayList<DevisResponseDTO> devisResponse ;
	
	
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public ArrayList<DevisResponseDTO> getDevisResponse() {
		return devisResponse;
	}
	public void setDevisResponse(ArrayList<DevisResponseDTO> devisResponse) {
		this.devisResponse = devisResponse;
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
	
	
}
