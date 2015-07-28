package manufacture.entity.product;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the material database table.
 * 
 */
@Entity
@Table(name="material")
public class Material implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_material")
	private Integer idMaterial;

	@Column(name="material_name")
	private String materialName;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="material")
	private List<Product> products;

	public Material() {
	}

	public Material(Integer idMaterial, String materialName) {
		super();
		this.idMaterial = idMaterial;
		this.materialName = materialName;
	}

	public Integer getIdMaterial() {
		return this.idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setMaterial(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setMaterial(null);

		return product;
	}

}