package by.aleksandr.vehicleservicestation.dao.vehicle;

import by.aleksandr.vehicleservicestation.entity.vehicle.FuelType;
import by.aleksandr.vehicleservicestation.entity.vehicle.Vehicle;

import java.util.List;

public interface VehicleDao {

    boolean save(Vehicle vehicle);

    List<Vehicle> getAll();

    Vehicle getById(long id);

    Vehicle getByVinNumber(String vinNumber);

    boolean contains(long id);

    boolean contains(Vehicle vehicle);

    boolean delete(long id);

    String updateMake(String newMake, long id);

    String updateModel(String newModel, long id);

    long updateYear(long newYear, long id);

    FuelType updateFuelType(FuelType newFuelType, long id);

    long updateValueEngine(long newValueEngine, long id);

    String updateVinNumber(String newVinNumber, long id);
}

