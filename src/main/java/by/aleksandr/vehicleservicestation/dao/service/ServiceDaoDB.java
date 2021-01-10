package by.aleksandr.vehicleservicestation.dao.service;

import by.aleksandr.vehicleservicestation.dao.user.UserDaoDB;
import by.aleksandr.vehicleservicestation.dao.vehicle.VehicleDaoDB;
import by.aleksandr.vehicleservicestation.entity.service.Service;
import by.aleksandr.vehicleservicestation.entity.service.ServiceStatus;
import by.aleksandr.vehicleservicestation.entity.user.User;
import by.aleksandr.vehicleservicestation.entity.vehicle.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoDB implements ServiceDao {
    Connection connection;
    UserDaoDB userDaoDB;
    VehicleDaoDB vehicleDaoDB;

    {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vehicleServiceStation", "postgres", "1111");
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public boolean save(Service service) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into services values(default ,?,?,?,?,?,?,?,?,?) RETURNING id");
            preparedStatement.setString(1, service.getTitle());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setLong(3, service.getUser().getId());
            preparedStatement.setLong(4, service.getVehicle().getId());
            preparedStatement.setString(5, service.getServiceStatus().name());
            preparedStatement.setLong(6, service.getCostOfParts());
            preparedStatement.setLong(7, service.getRepairСost());
            preparedStatement.setString(8, service.getRenovationDate());
            preparedStatement.setString(9, service.getWarranty());
            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return true;
    }
    @Override
    public boolean deleteById(int id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from services where id = ?");
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
    public List<Service> getAll() {
        List<Service> services = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from services");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                Long id_user = resultSet.getLong(4);
                User user = userDaoDB.getById(id_user);
                Long id_vehicle = resultSet.getLong(5);
                Vehicle vehicle = vehicleDaoDB.getById(id_vehicle);
                ServiceStatus serviceStatus = ServiceStatus.valueOf(resultSet.getString(6));
                Long costOfParts = resultSet.getLong(7);
                Long repairСost = resultSet.getLong(8);
                String renovationDate = resultSet.getString(9);
                String warranty = resultSet.getString(10);
                Service service = new Service(id, title, description, user, vehicle, serviceStatus, costOfParts,repairСost, renovationDate, warranty);
                services.add(service);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return services;
    }


    @Override
    public Service getById(int id) {
        Service service = new Service();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from services where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            Long id_user = resultSet.getLong(4);
            User user = userDaoDB.getById(id_user);
            Long id_vehicle = resultSet.getLong(5);
            Vehicle vehicle = vehicleDaoDB.getById(id_vehicle);
            ServiceStatus serviceStatus = ServiceStatus.valueOf(resultSet.getString(6));
            Long costOfParts = resultSet.getLong(7);
            Long repairСost = resultSet.getLong(8);
            String renovationDate = resultSet.getString(9);
            String warranty = resultSet.getString(10);
            service.setId(id);
            service.setTitle(title);
            service.setDescription(description);
            service.setUser(user);
            service.setVehicle(vehicle);
            service.setServiceStatus(serviceStatus);
            service.setCostOfParts(costOfParts);
            service.setRepairСost(repairСost);
            service.setRenovationDate(renovationDate);
            service.setWarranty(warranty);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Service> getByUser(User user) {
        long id = user.getId();
        List<Service> services = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select from services where id_user  = id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_service = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                Long id_vehicle = resultSet.getLong(5);
                Vehicle vehicle = vehicleDaoDB.getById(id_vehicle);
                ServiceStatus serviceStatus = ServiceStatus.valueOf(resultSet.getString(6));
                Long costOfParts = resultSet.getLong(7);
                Long repairСost = resultSet.getLong(8);
                String renovationDate = resultSet.getString(9);
                String warranty = resultSet.getString(10);
                Service service = new Service(id, title, description, user, vehicle, serviceStatus, costOfParts,repairСost, renovationDate, warranty);
                services.add(service);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return services;
    }

    @Override
    public List<Service> getByVehicle(Vehicle vehicle) {
        long id_vehicle = vehicle.getId();
        List<Service> services = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select from services where id_vehicle  = id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                Long id_user = resultSet.getLong(4);
                User user = new UserDaoDB().getById(id_user) ;
                ServiceStatus serviceStatus = ServiceStatus.valueOf(resultSet.getString(6));
                Long costOfParts = resultSet.getLong(7);
                Long repairСost = resultSet.getLong(8);
                String renovationDate = resultSet.getString(9);
                String warranty = resultSet.getString(10);
                Service service = new Service(id, title, description, user, vehicle, serviceStatus, costOfParts,repairСost, renovationDate, warranty);
                services.add(service);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return services;
    }

    @Override
    public List<Service> getByServiceStatus(ServiceStatus serviceStatus) {
        List<Service> services = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select from services where serviceStatus  = serviceStatus");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_service = resultSet.getInt(1);
                Service service = new ServiceDaoDB().getById(id_service);
                services.add(service);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return services;
    }

    @Override
    public boolean contains(long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from services");
            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}