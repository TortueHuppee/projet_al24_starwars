package manufacture.ws.order;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IOrder {
	
	public OrderResponseDTO toOrder(@WebParam(name = "orderRequest")OrderRequestDTO orderRequest) ;

}
