package by.aleksandr.vehicleservicestation.service;

import by.aleksandr.vehicleservicestation.dao.vehicle.InMemoryVehicleDao;
import by.aleksandr.vehicleservicestation.dao.vehicle.VehicleDaoDB;
import by.aleksandr.vehicleservicestation.entity.vehicle.FuelType;
import by.aleksandr.vehicleservicestation.entity.vehicle.Vehicle;
import by.aleksandr.vehicleservicestation.errors.IdDoesntExist;
import by.aleksandr.vehicleservicestation.errors.IsEmptyException;
import by.aleksandr.vehicleservicestation.errors.VehicleByIdDoesntMatch;
import by.aleksandr.vehicleservicestation.errors.VehicleByVinNumberDoesntExist;

import java.util.List;

public class VehicleService {
    private VehicleDaoDB vehicleDaoDB = new VehicleDaoDB();
    private InMemoryVehicleDao inMemoryVehicleDao = new InMemoryVehicleDao();

    public boolean synchronizedSave(Vehicle vehicle) {
        if (inMemoryVehicleDao.contains(vehicle) && vehicleDaoDB.contains(vehicle)) {
            return false;
        } else {
            vehicleDaoDB.save(vehicle);
            inMemoryVehicleDao.save(vehicleDaoDB.getByVinNumber(vehicle.getVinNumber()));

        }
        return true;
    }

    public boolean synchronizedSaveFirst(Vehicle vehicle) {
        if (inMemoryVehicleDao.contains(vehicle) && vehicleDaoDB.contains(vehicle)) {
            return false;
        } else {
            inMemoryVehicleDao.save(vehicle);
            vehicleDaoDB.save(vehicle);
        }
        return true;
    }

    public void synchronizedDelete(long id) {
        if (inMemoryVehicleDao.contains(id) && vehicleDaoDB.contains(id)) {
            if (inMemoryVehicleDao.getById(id).equals(vehicleDaoDB.getById(id))) {
                inMemoryVehicleDao.delete(id);
                vehicleDaoDB.delete(id);
                return;
            }
            throw new VehicleByIdDoesntMatch();
        }
        throw new IdDoesntExist();
    }

    public void synchronizedUpdateName(String newVinNumber, long id) {
        if (inMemoryVehicleDao.contains(id) && vehicleDaoDB.contains(id)) {
            if (inMemoryVehicleDao.getById(id).equals(vehicleDaoDB.getById(id))) {
                inMemoryVehicleDao.updateVinNumber(newVinNumber, id);
                vehicleDaoDB.updateVinNumber(newVinNumber, id);
                return;
            }
            throw new VehicleByIdDoesntMatch();
        }
        throw new IdDoesntExist();
    }

    public void synchronizedUpdateMake(String newMake, long id) {
        if (inMemoryVehicleDao.contains(id) && vehicleDaoDB.contains(id)) {
            if (inMemoryVehicleDao.getById(id).equals(vehicleDaoDB.getById(id))) {
                inMemoryVehicleDao.updateMake(newMake, id);
                vehicleDaoDB.updateMake(newMake, id);
                return;
            }
            throw new VehicleByIdDoesntMatch();
        }
        throw new IdDoesntExist();
    }

    public void synchronizedUpdateModel(String newModel, long id) {
        if (inMemoryVehicleDao.contains(id) && vehicleDaoDB.contains(id)) {
            if (inMemoryVehicleDao.getById(id).equals(vehicleDaoDB.getById(id))) {
                inMemoryVehicleDao.updateModel(newModel, id);
                vehicleDaoDB.updateModel(newModel, id);
                return;
            }
            throw new VehicleByIdDoesntMatch();
        }
        throw new IdDoesntExist();
    }
    public void synchronizedUpdateYear(long newYear, long id) {
        if (inMemoryVehicleDao.contains(id) && vehicleDaoDB.contains(id)) {
            if (inMemoryVehicleDao.getById(id).equals(vehicleDaoDB.getById(id))) {
                inMemoryVehicleDao.updateYear(newYear, id);
                vehicleDaoDB.updateYear(newYear, id);
                return;
            }
            throw new VehicleByIdDoesntMatch();
        }
        throw new IdDoesntExist();
    }

    public void synchronizedUpdateEngineValue(long newEngineValue, long id) {
        if (inMemoryVehicleDao.contains(id) && vehicleDaoDB.contains(id)) {
            if (inMemoryVehicleDao.getById(id).equals(vehicleDaoDB.getById(id))) {
                inMemoryVehicleDao.updateYear(newEngineValue, id);
                vehicleDaoDB.updateYear(newEngineValue, id);
                return;
            }
            throw new VehicleByIdDoesntMatch();
        }
        throw new IdDoesntExist();
    }

    public void synchronizedUpdateFuelType(FuelType newFuelType, long id) {
        if (inMemoryVehicleDao.contains(id) && vehicleDaoDB.contains(id)) {
            if (inMemoryVehicleDao.getById(id).equals(vehicleDaoDB.getById(id))) {
                inMemoryVehicleDao.updateFuelType(newFuelType, id);
                vehicleDaoDB.updateFuelType(newFuelType, id);
                return;
            }
            throw new VehicleByIdDoesntMatch();
        }
        throw new IdDoesntExist();
    }


    public List<Vehicle> getAllFromDB(){
        if (vehicleDaoDB.getAll().isEmpty()){
            throw new IsEmptyException();
        }
        return vehicleDaoDB.getAll();
    }


    public Vehicle getByVehicleVinNumberFromInMemory(String vinNumber){
        if (inMemoryVehicleDao.getByVinNumber(vinNumber) == null){
            throw new VehicleByVinNumberDoesntExist();
        }
        return inMemoryVehicleDao.getByVinNumber(vinNumber);
    }

    public Vehicle getByIdFromInMemory(long id){
        if (vehicleDaoDB.getById(id) == null){
            throw new VehicleByIdDoesntMatch();
        }
        return inMemoryVehicleDao.getById(id);
    }
}
