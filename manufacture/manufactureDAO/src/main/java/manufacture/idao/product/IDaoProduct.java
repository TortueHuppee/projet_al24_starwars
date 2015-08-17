package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Product;
import manufacture.entity.user.User;

public interface IDaoProduct {

	//M�thodes
	List<Product> getAllProductByProductRef(int idProducRef);
	
	void updateProductStock(int idProduct, int quantitySend);
	
	void checkProductStock(int idProduct);
	
	//Gestion de base de donn�es
	void addProduct(Product product);
	
	void deleteProduct(Product product);
	
	void updateProduct(Product product);
	
	//Catalogue - Fiche d�tail d'un produit

	List<Product> getAllProduct();
	
	List<Product> getAllProductBySpaceShipRef(int idSpaceShipSelected);
	
    //Panier - Fiche d�tail d'un produit
	Product getProductByIdProduct (int idProduct);

	//Profil utilisateur
	List<Product> getProductSendByUser(User user);

	List<Product> getProductNotSendByUser(User user);
}
