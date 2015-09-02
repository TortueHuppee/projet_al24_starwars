package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Product;
import manufacture.entity.user.User;

public interface IDaoProduct {

	//Methodes
	List<Product> getAllProductByProductRef(int idProducRef);
	
	void updateProductStock(int idProduct, int quantitySend);
	
	void checkProductStock(int idProduct);
	
	//Gestion de base de donnees
	void addProduct(Product product);
	
	void deleteProduct(Product product);
	
	void updateProduct(Product product);
	
	//Catalogue - Fiche detail d'un produit

	List<Product> getAllProduct();
	
	List<Product> getAllProductBySpaceShipRefAndName(int idSpaceShipSelected, String name);
	
	 List<Product> getAllProductByName(String name);
	
    //Panier - Fiche detail d'un produit
	Product getProductByIdProduct (int idProduct);

	//Profil utilisateur
	List<Product> getProductSendByUser(User user);

	List<Product> getProductNotSendByUser(User user);
   
}
