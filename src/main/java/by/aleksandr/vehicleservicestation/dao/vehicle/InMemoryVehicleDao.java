package by.aleksandr.vehicleservicestation.dao.vehicle;

import by.aleksandr.vehicleservicestation.entity.vehicle.FuelType;
import by.aleksandr.vehicleservicestation.entity.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class InMemoryVehicleDao implements VehicleDao {
    private static List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public boolean save(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }


    @Override
    public List<Vehicle> getAll() {
        return vehicles;
    }

    @Override
    public Vehicle getById(long id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public Vehicle getByVinNumber(String vinNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVinNumber().equals(vinNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public boolean contains(long id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Vehicle vehicle) {
        for (Vehicle vehicle1 : vehicles) {
            if (vehicle1.equals(vehicle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getId() == id) {
                vehicles.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String updateMake(String newMake, long id) {
        getById(id).setMake(newMake);
        return newMake;
    }

    @Override
    public String updateModel(String newModel, long id) {
        getById(id).setModel(newModel);
        return newModel;
    }

    @Override
    public long updateYear(long newYear, long id) {
        getById(id).setYear(newYear);
        return newYear;
    }

    @Override
    public FuelType updateFuelType(FuelType newFuelType, long id) {
        getById(id).setFuelType(newFuelType);
        return newFuelType;
    }

    @Override
    public long updateValueEngine(long newValueEngine, long id) {
        getById(id).setValueEngine(newValueEngine);
        return newValueEngine;
    }

    @Override
    public String updateVinNumber(String newVinNumber, long id) {
        getById(id).setVinNumber(newVinNumber);
        return newVinNumber;
    }
}
