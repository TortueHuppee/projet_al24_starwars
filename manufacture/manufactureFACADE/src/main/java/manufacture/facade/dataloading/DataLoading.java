package manufacture.facade.dataloading;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.ProductRef;
import manufacture.entity.user.City;
import manufacture.ibusiness.dataloading.IBusinessDataLoading;
import manufacture.ifacade.dataloading.IDataLoading;
@Service
public class DataLoading implements IDataLoading {
	
    @Autowired
	private IBusinessDataLoading businessDataLoading;
	
    public List<City> loadCities(){
		return businessDataLoading.loadCity();
	}
}
