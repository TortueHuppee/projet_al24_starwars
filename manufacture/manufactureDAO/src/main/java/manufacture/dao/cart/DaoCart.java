package manufacture.dao.cart;

import java.util.Date;
import java.util.List;
import java.util.Random;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.user.User;
import manufacture.idao.cart.IDaoCart;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DaoCart implements IDaoCart {

    private Logger log = Logger.getLogger(DaoCart.class);
    private SessionFactory sf;

    @Override
    public Cart validatePayment(Cart cart) {
        Session session = sf.getCurrentSession();
        cart.setDatePayment(new Date());
        cart.setIsPaid(true);

        //Génération aléatoire du numéro de transaction bancaire
        Random rand = new Random();
        int transactionNumber = rand.nextInt(999999999 - 100000000 + 1) + 100000000;
        cart.setTransactionNumber(transactionNumber);

        if(cart.getIdCart( ) == 0){
            session.save(cart);
            for(CartProduct cp : cart.getCartProducts()){
                cp.setCart(cart);
                session.save(cp);
            }
        }else{ 
            session.update(cart);
        }
        return cart; 
    } 

    @Override
    public int createNewCart(int idUser) {
        Session session = sf.getCurrentSession();
        Cart cart = new Cart();
        User user = new User();
        user.setIdUser(idUser);
        cart.setUser(user);
        cart.setIsPaid(false);
        cart.setIsValidated(false);
        session.save(cart);
        return cart.getIdCart();
    }

    @Override
    public void sendRecall(int idUser) {
        Session session = sf.getCurrentSession();

        Query hql_get_nbRecall = session.createQuery("SELECT u.nbRecall FROM User u WHERE u.idUser = :paramId");
        hql_get_nbRecall.setInteger("paramId",idUser);
        hql_get_nbRecall.setMaxResults(1);
        int nombreRappelActuel = ( int ) hql_get_nbRecall.uniqueResult( );

        if (nombreRappelActuel == 3)
        {
            Query hql_update_nbRecall = session.createQuery("UPDATE User SET isBlackListed = TRUE WHERE idUser = :paramId");
            hql_update_nbRecall.setInteger("paramId",idUser);
            hql_update_nbRecall.executeUpdate( );
        }
        else
        {
            Query hql_update_nbRecall = session.createQuery("UPDATE User SET nbRecall = :newNbRecall WHERE idUser = :paramId");
            hql_update_nbRecall.setInteger("newNbRecall",nombreRappelActuel++);
            hql_update_nbRecall.setInteger("paramId",idUser);
            hql_update_nbRecall.executeUpdate( );
        }
    }	

    @Override
    public List<CartProduct> getAllCartProductByCart(int idCart) {
        Session session = sf.getCurrentSession();
        Query hql = session.createQuery("SELECT cp FROM CartProduct cp WHERE cp.cart.idCart = :idCart");
        hql.setParameter("idCart", idCart);
        List<CartProduct> resultat = hql.list();

        return resultat;
    }

    @Override
    public Cart getCartByIdCart(int idCart) {
        Session session = sf.getCurrentSession();
        Query hql = session.createQuery("SELECT c FROM Cart c WHERE c.idCart = :idCart");
        hql.setParameter("idCart", idCart);
        return (Cart) hql.list().get(0);
    }

    @Override
    public void updateCart(Cart cart) {
        Session session = sf.getCurrentSession();
        session.update(cart);
    }
    
    @Autowired
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}