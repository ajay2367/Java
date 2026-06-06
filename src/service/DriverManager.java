package service;
import models.Driver;
import enums.DriverStatus;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager {
    private final ConcurrentHashMap<Integer, Driver> drivers;

    public DriverManager() {
        this.drivers = new ConcurrentHashMap<>();
    }

    public void addDriver(Driver driver) {
        drivers.put(driver.getDriverId(),driver);
    }

    public Driver getDriver(int driverId) {
        return drivers.get(driverId);
    }

    public void markDriverAvailable(int driverId) {
        Driver driver = drivers.get(driverId);
        if(driver != null) {
            driver.setStatus(DriverStatus.AVAILABLE);
        }
    }

    public void markDriverBusy(int driverId) {
        Driver driver = drivers.get(driverId);
        if(driver != null) {
            driver.setStatus(DriverStatus.BUSY);
        }
     }

     public int getTotalDrivers() {
        return drivers.size();
     }
}
