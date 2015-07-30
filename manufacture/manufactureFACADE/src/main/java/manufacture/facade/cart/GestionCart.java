package manufacture.facade.cart;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.ifacade.cart.IGestionCart;
import manufacture.ifacade.cart.IPaiement;

@Service
public class GestionCart implements IGestionCart {
   
    private Logger log = Logger.getLogger(GestionCart.class);
    
	IBusinessCart businessCart;

    @Override
    public List<Product> getAllProductByCart(int paramIdCart) {
        return businessCart.getAllProductByCart(paramIdCart);
    }

    @Override
    public double getTotalPrice(int paramIdCart) {
        return businessCart.getTotalPrice(paramIdCart);
    }

    @Override
    public void validateCommande(Cart paramCart) {
        
//        if (paramCart.getUser())
        businessCart.orderSpecificCommande(paramCart);
        
       //particulier => si paiement accepté par la banque
            //on créé le panier et on le valide***
            //on créé les sous-produits du panier***
            //on valide la panier et le paiement
        
        
      //professionnel => deux cas
            //on créé le panier et on le valide***
            //on créé les sous-produits du panier***
        
                //paiement différé
                    //on valide le panier uniquement
                    //prévoir méthode pou payer ultérieurement
                //paiement immédiat
                    //on valide la panier et le paiement

    }

    @Override
    public void validatePayment(Cart paramCart) {
        businessCart.validatePayment(paramCart);
    }
    
    @Autowired
    public void setBuCart(IBusinessCart paramBuCart) {
        businessCart = paramBuCart;
    }
}
