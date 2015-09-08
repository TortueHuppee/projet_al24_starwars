package manufacture.ws.WS;

import javax.jws.WebParam;
import javax.jws.WebService;

import manufacture.ws.DTO.OrderRequestDTO;
import manufacture.ws.DTO.OrderResponseDTO;

@WebService
public interface IOrder {
	
	public OrderResponseDTO toOrder(@WebParam(name = "orderRequest")OrderRequestDTO orderRequest) ;

}
