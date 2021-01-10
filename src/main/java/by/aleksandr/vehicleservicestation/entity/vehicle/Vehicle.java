package by.aleksandr.vehicleservicestation.entity.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private long id;
    private String make;
    private String model;
    private long year;
    private FuelType fuelType;
    private long valueEngine;
    private String vinNumber;

}
