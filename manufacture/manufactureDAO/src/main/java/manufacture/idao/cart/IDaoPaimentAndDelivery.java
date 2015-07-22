package manufacture.idao.cart;

import java.util.List;

import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;

public interface IDaoPaimentAndDelivery {
	
		List<PaymentType> getAllPaymentType();
		
		List<Delivery> getAllDeliveryType();

}
