package manufacture.idao.product;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.ArtisanProduct;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Product;
import manufacture.entity.product.UsedProduct;
import manufacture.entity.user.User;

public interface IDaoProduct {

	//Méthodes
	List<ConstructorProduct> getAllProductByProductRef(int idProducRef);
	
	void updateProductStock(int idProduct, int quantitySend);
	
	void checkProductStock(int idProduct);
	
	//Gestion de base de données
	void addProduct(Product product);
	
	void deleteProduct(Product product);
	
	void updateProduct(Product product);
	
	//Catalogue - Fiche détail d'un produit

	List<Product> getAllProduct();

	List<ConstructorProduct> getAllConstructorProduct();
	
    List<ArtisanProduct> getAllArtisanProduct();

    List<UsedProduct> getAllUsedProduct();
	
    //Panier - Fiche détail d'un produit
	Product getProductByIdProduct (int idProduct);

	//Profil utilisateur
	List<Product> getProductSendByUser(User user);
	
}
