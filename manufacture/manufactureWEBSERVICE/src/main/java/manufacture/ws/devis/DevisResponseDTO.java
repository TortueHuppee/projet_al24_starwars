package manufacture.ws.devis;

public class DevisResponseDTO {

	private Integer idProductRef ;
	private Integer idColor ;
	private Integer idMaterial ;
	private Integer idConstructor ;
	private Integer quantity ;
	
	private Double price ;
	private String availability ;
	
	public Integer getIdProductRef() {
		return idProductRef;
	}
	public void setIdProductRef(Integer idProductRef) {
		this.idProductRef = idProductRef;
	}
	public Integer getIdColor() {
		return idColor;
	}
	public void setIdColor(Integer idColor) {
		this.idColor = idColor;
	}
	public Integer getIdMaterial() {
		return idMaterial;
	}
	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}
	public Integer getIdConstructor() {
		return idConstructor;
	}
	public void setIdConstructor(Integer idConstructor) {
		this.idConstructor = idConstructor;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
