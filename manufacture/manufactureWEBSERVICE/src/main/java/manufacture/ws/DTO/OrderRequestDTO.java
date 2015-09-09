package manufacture.ws.DTO;

import java.util.ArrayList;
import java.util.Date;

public class OrderRequestDTO {
	
	// Pour identifier le client
	private String emailUser ;		
		
	// donnees pour le paiement
//	private String IBAN ;
	private Long numeroCarte ;
	private Date dateFinValidite ;
	private int cryptogramme ;
	private String PaymentType ;
	
	// Liste des produits à commander
	private ArrayList<DevisResponseDTO> ListProductToOrder ;
	
	
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public String getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	public Long getNumeroCarte() {
		return numeroCarte;
	}
	public void setNumeroCarte(Long numeroCarte) {
		this.numeroCarte = numeroCarte;
	}
	public Date getDateFinValidite() {
		return dateFinValidite;
	}
	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}
	public int getCryptogramme() {
		return cryptogramme;
	}
	public void setCryptogramme(int cryptogramme) {
		this.cryptogramme = cryptogramme;
	}
	public ArrayList<DevisResponseDTO> getListProductToOrder() {
		return ListProductToOrder;
	}
	public void setListProductToOrder(ArrayList<DevisResponseDTO> listProductToOrder) {
		ListProductToOrder = listProductToOrder;
	}
		
}
