package simulation;
import enums.DriverStatus;
import enums.RideStatus;
import models.Ride;
import service.DriverManager;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RideTask implements Runnable {
    private final Ride ride;
    private final DriverManager driverManager;

    public RideTask(Ride ride, DriverManager driverManager) {
        this.ride = ride;
        this.driverManager = driverManager;
    }

    @Override
    public void run() {
        try{
            ride.updateStatus(RideStatus.DRIVER_ARRIVING);
            TimeUnit.SECONDS.sleep(2);
            ride.updateStatus(
                    RideStatus.IN_PROGRESS
            );
            System.out.println(
                    "Ride " + ride.getRideId()
                            + " started with Driver "
                            + ride.getDriver().getDriverId()
            );
            int duration =
                    ThreadLocalRandom.current()
                            .nextInt(5, 11);

            TimeUnit.SECONDS.sleep(duration);
            double fare =
                    calculateFare(duration);

            ride.updateFare(fare);

            ride.updateStatus(
                    RideStatus.COMPLETED
            );

            driverManager.markDriverAvailable(
                    ride.getDriver().getDriverId()
            );

            System.out.println(
                    "Ride " + ride.getRideId()
                            + " completed. Fare = "
                            + fare
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(
                    "Ride "
                            + ride.getRideId()
                            + " interrupted."
            );
        }
    }

    private double calculateFare(
            int duration) {

        double baseFare = 50.0;

        double perMinuteFare = 10.0;

        return baseFare +
                (duration * perMinuteFare);
    }
}
