package manufacture.ws.DTO;

public class DevisRequestDTO {
	
	private String idProductRef ;
	private String idColor ;
	private String idMaterial ;
	private String idConstructor ;
	private String quantity ;
	
	public String getIdProductRef() {
		return idProductRef;
	}
	public void setIdProductRef(String idProductRef) {
		this.idProductRef = idProductRef;
	}
	public String getIdColor() {
		return idColor;
	}
	public void setIdColor(String idColor) {
		this.idColor = idColor;
	}
	public String getIdMaterial() {
		return idMaterial;
	}
	public void setIdMaterial(String idMaterial) {
		this.idMaterial = idMaterial;
	}
	public String getIdConstructor() {
		return idConstructor;
	}
	public void setIdConstructor(String idConstructor) {
		this.idConstructor = idConstructor;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}
