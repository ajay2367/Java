package models;
import enums.DriverStatus;
import enums.Gender;

public class Driver {
    private final int driverId;
    private final String name;
    private final Gender gender;
    private Location currentLocation;
    private DriverStatus status;
    private double rating;

    public Driver(int driverId,String name, Gender gender, Location currentLocation, DriverStatus status, double rating) {
        this.gender = gender;
        this.currentLocation = currentLocation;
        this.driverId = driverId;
        this.name = name;
        this.rating = rating;

        this.status = DriverStatus.AVAILABLE;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", currentLocation=" + currentLocation +
                ", status=" + status +
                ", rating=" + rating +
                '}';
    }
}
