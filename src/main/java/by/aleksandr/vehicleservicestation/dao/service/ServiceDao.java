package by.aleksandr.vehicleservicestation.dao.service;

import by.aleksandr.vehicleservicestation.entity.service.Service;
import by.aleksandr.vehicleservicestation.entity.service.ServiceStatus;
import by.aleksandr.vehicleservicestation.entity.user.User;
import by.aleksandr.vehicleservicestation.entity.vehicle.Vehicle;

import java.util.List;

public interface ServiceDao {

    boolean save(Service service);

    boolean deleteById(int id);

    List<Service> getAll();

    Service getById(int id);

    List<Service> getByUser(User user);

    List<Service> getByVehicle(Vehicle vehicle);

    List<Service> getByServiceStatus(ServiceStatus serviceStatus);

    boolean contains(long id);
}
