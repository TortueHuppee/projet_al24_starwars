package manufacture.ws.DTO;

import manufacture.ws.WS.IDevis;

public class DevisRequestDTO {
	
	private Integer idProductRef ;
	private Integer idColor ;
	private Integer idMaterial ;
	private Integer idConstructor ;
	private Integer quantity ;
	
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
	
}
