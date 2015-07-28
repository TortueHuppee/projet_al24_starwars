package manufacture.business.dataloading;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.user.City;
import manufacture.ibusiness.dataloading.IBusinessDataLoading;
import manufacture.idao.dataloading.IDaoCity;
@Service
public class BusinessDataLoading implements IBusinessDataLoading {

    @Autowired
    private IDaoCity daoCity;
    
    public List<City> loadCity(){
        return daoCity.getAllCities();
    }
}
