package by.aleksandr.vehicleservicestation.service;

import by.aleksandr.vehicleservicestation.dao.service.InMemoryServiceDao;
import by.aleksandr.vehicleservicestation.dao.service.ServiceDaoDB;
import by.aleksandr.vehicleservicestation.entity.service.Service;
import by.aleksandr.vehicleservicestation.errors.IdDoesntExist;
import by.aleksandr.vehicleservicestation.errors.IsEmptyException;
import by.aleksandr.vehicleservicestation.errors.UsersByIdDoesntMatch;

import java.util.List;

public class ServiceService {
    private ServiceDaoDB serviceDaoDB = new ServiceDaoDB();
    private InMemoryServiceDao inMemoryServiceDao = new InMemoryServiceDao();

    public boolean synchronizedSave(Service service) {
        if (inMemoryServiceDao.contains(service.getId()) && serviceDaoDB.contains(service.getId())) {
            return false;
        } else {
            serviceDaoDB.save(service);
            inMemoryServiceDao.save(service);
        }
        return true;
    }

    public void synchronizedDelete(int id) {
        if (inMemoryServiceDao.contains(id) && serviceDaoDB.contains(id)) {
            if (inMemoryServiceDao.getById(id).equals(serviceDaoDB.getById(id))) {
                inMemoryServiceDao.deleteById(id);
                serviceDaoDB.deleteById(id);
                return;
            }
            throw new UsersByIdDoesntMatch();
        }
        throw new IdDoesntExist();
    }
    public List<Service> getAllFromInMemory(){
        if (inMemoryServiceDao.getAll().isEmpty()){
            throw new IsEmptyException();
        }
        return inMemoryServiceDao.getAll();
    }

    public List<Service> getAllFromDB(){
        if (serviceDaoDB.getAll().isEmpty()){
            throw new IsEmptyException();
        }
        return serviceDaoDB.getAll();
    }

}

