package models;
import enums.DriverStatus;
import enums.RideStatus;
import java.time.LocalDateTime;

public class Ride {
    private final int rideId;
    private final RideRequest rideRequest;
    private final User user;
    private Driver driver;
    private RideStatus status;
    private final LocalDateTime createdTime;
    private LocalDateTime completedTime;
    private double fare;

    public Ride(int rideId, RideRequest rideRequest, RideStatus status, Driver driver) {
        this.rideId = rideId;
        this.rideRequest = rideRequest;
        this.user =rideRequest.getUser() ;
        this.status = status;
        this.driver = driver;
        this.status = RideStatus.DRIVER_ASSIGNED;
        this.createdTime = LocalDateTime.now();
    }

    public int getRideId() {
        return rideId;
    }

    public RideRequest getRideRequest() {
        return rideRequest;
    }

    public User getUser() {
        return user;
    }

    public Driver getDriver() {
        return driver;
    }

    public RideStatus getStatus() {
        return status;
    }

    public double getFare() {
        return fare;
    }

    public void updateFare (double fare) {
        this.fare = fare;
    }

    public void updateStatus(RideStatus status) {
        this.status = status;
        if(status == RideStatus.COMPLETED) {
            this.completedTime = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideId=" + rideId +
                ", rideRequest=" + rideRequest +
                ", user=" + user +
                ", driver=" + driver +
                ", status=" + status +
                ", createdTime=" + createdTime +
                ", completedTime=" + completedTime +
                ", fare=" + fare +
                '}';
    }
}
