package by.aleksandr.vehicleservicestation.dao.service;

import by.aleksandr.vehicleservicestation.entity.service.Service;
import by.aleksandr.vehicleservicestation.entity.service.ServiceStatus;
import by.aleksandr.vehicleservicestation.entity.user.User;
import by.aleksandr.vehicleservicestation.entity.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class InMemoryServiceDao implements ServiceDao {
    private List<Service> services = new ArrayList<>();

    @Override
    public boolean save(Service service) {
        return services.add(service);
    }

    @Override
    public boolean deleteById(int id) {
        services.remove(id);
        return true;
    }

    @Override
    public List<Service> getAll() {
        return services;
    }

    @Override
    public Service getById(int id) {
        for (Service serviceByID : services) {
            if (serviceByID.getId() == id) {
                return serviceByID;
            }
        }
        return null;
    }

    @Override
    public List<Service> getByUser(User user) {
        List<Service> servicesByUser = new ArrayList<>();
        for (Service serviceByUser : services) {
            if (serviceByUser.getUser().equals(user)) {
                servicesByUser.add(serviceByUser);
            }
            return servicesByUser;
        }
        return null;
    }

    @Override
    public List<Service> getByVehicle(Vehicle vehicle) {
        List<Service> servicesByVehicle = new ArrayList<>();
        for (Service serviceByVehicle : services) {
            if (serviceByVehicle.getVehicle().equals(vehicle)) {
                servicesByVehicle.add(serviceByVehicle);
            }
            return servicesByVehicle;
        }
        return null;
    }

    @Override
    public List<Service> getByServiceStatus(ServiceStatus serviceStatus) {
        List<Service> servicesByStatus = new ArrayList<>();
        for (Service serviceByStatus : services) {
            if (serviceByStatus.getServiceStatus().equals(serviceStatus)) {
                servicesByStatus.add(serviceByStatus);
            }
            return servicesByStatus;
        }
        return null;
    }

    @Override
    public boolean contains(long id) {
        for (Service serviceById : services) {
            if (serviceById.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
