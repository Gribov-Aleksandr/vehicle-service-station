package by.aleksandr.vehicleservicestation.dao.vehicle;

import by.aleksandr.vehicleservicestation.entity.vehicle.FuelType;
import by.aleksandr.vehicleservicestation.entity.vehicle.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoDB implements VehicleDao {
    Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vehicleServiceStation", "postgres", "1111");
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public boolean save(Vehicle vehicle) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into vehicles values (default,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, vehicle.getMake());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setLong(3, vehicle.getYear());
            preparedStatement.setString(4, String.valueOf(vehicle.getFuelType()));
            preparedStatement.setLong(5, vehicle.getValueEngine());
            preparedStatement.setString(6, vehicle.getVinNumber());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vehicles");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                long year =  resultSet.getLong(4);
                FuelType fuelType =  FuelType.valueOf(resultSet.getString(5));
                long valueEngine =  resultSet.getLong(6);
                String vinNumber = resultSet.getString(7);
                Vehicle vehicle = new Vehicle(id, make, model, year, fuelType, valueEngine, vinNumber);
                vehicles.add(vehicle);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public Vehicle getById(long id) {
        Vehicle vehicle = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicles where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long VehicleID = resultSet.getLong(1);
            String make = resultSet.getString(2);
            String model = resultSet.getString(3);
            long year =  resultSet.getLong(4);
            FuelType fuelType =  FuelType.valueOf(resultSet.getString(5));
            long valueEngine =  resultSet.getLong(6);
            String vinNumber = resultSet.getString(7);
            vehicle = new Vehicle(VehicleID, make, model, year, fuelType, valueEngine, vinNumber);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vehicle;
    }

    @Override
    public Vehicle getByVinNumber(String vinNumber) {
        Vehicle vehicle = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicles where vinNumber = ?");
            preparedStatement.setString(1, vinNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long id = resultSet.getLong(1);
            String make = resultSet.getString(2);
            String model = resultSet.getString(3);
            long year =  resultSet.getLong(4);
            FuelType fuelType =  FuelType.valueOf(resultSet.getString(5));
            long valueEngine =  resultSet.getLong(6);
            String vehicleVinNumber = resultSet.getString(7);
            vehicle = new Vehicle(id, make, model, year, fuelType, valueEngine, vehicleVinNumber);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vehicle;
    }

    @Override
    public boolean contains(long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from vehicles");
            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    return true;
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(Vehicle vehicle) {
        List<Vehicle> vehicles = getAll();
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicle.equals(vehicles.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from vehicles where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException throwable) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public String updateMake(String newMake, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicle set make = ? where id = ?");
            preparedStatement.setString(1, newMake);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newMake;
    }

    @Override
    public String updateModel(String newModel, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicle set model = ? where id = ?");
            preparedStatement.setString(1, newModel);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newModel;
    }

    @Override
    public long updateYear(long newYear, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicle set year = ? where id = ?");
            preparedStatement.setLong(1, newYear);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newYear;
    }

    @Override
    public FuelType updateFuelType(FuelType newFuelType, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicles set fuelType = ? where id = ?");
            preparedStatement.setString(1, String.valueOf(newFuelType));
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newFuelType;
    }

    @Override
    public long updateValueEngine(long newValueEngine, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicle set valueEngine = ? where id = ?");
            preparedStatement.setLong(1, newValueEngine);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newValueEngine;
    }

    @Override
    public String updateVinNumber(String newVinNumber, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicle set vinNumber = ? where id = ?");
            preparedStatement.setString(1, newVinNumber);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newVinNumber;
    }
}
