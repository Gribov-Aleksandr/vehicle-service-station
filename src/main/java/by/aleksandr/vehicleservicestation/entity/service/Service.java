package by.aleksandr.vehicleservicestation.entity.service;

import by.aleksandr.vehicleservicestation.entity.user.User;
import by.aleksandr.vehicleservicestation.entity.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    private long id;
    private String title;
    private String description;
    private User user;
    private Vehicle vehicle;
    private ServiceStatus serviceStatus;
    private long costOfParts;
    private long repairСost;
    private String renovationDate;
    private String warranty;

}
