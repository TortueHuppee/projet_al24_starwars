package manufacture.ifacade.dataloading;

import java.util.List;

import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.ProductRef;
import manufacture.entity.user.City;

public interface IDataLoading {

    //Adresse
    List<City> loadCities();

}
