package manufacture.web.catalogBean;

public class ItemConstructeur {

	private Integer idConstructeur;
	private Integer idTypeProduct;
	private String nomConstructeur;
	
	public ItemConstructeur() {
		
	}
	
	public ItemConstructeur(Integer idConstructeur, Integer idTypeProduct,
			String nomConstructeur) {
		super();
		this.idConstructeur = idConstructeur;
		this.idTypeProduct = idTypeProduct;
		this.nomConstructeur = nomConstructeur;
	}
	
	public Integer getIdConstructeur() {
		return idConstructeur;
	}
	public void setIdConstructeur(Integer idConstructeur) {
		this.idConstructeur = idConstructeur;
	}
	public Integer getIdTypeProduct() {
		return idTypeProduct;
	}
	public void setIdTypeProduct(Integer idTypeProduct) {
		this.idTypeProduct = idTypeProduct;
	}
	public String getNomConstructeur() {
		return nomConstructeur;
	}
	public void setNomConstructeur(String nomConstructeur) {
		this.nomConstructeur = nomConstructeur;
	}
}
