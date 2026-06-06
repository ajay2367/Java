package simulation;
import models.*;
import java.util.concurrent.BlockingQueue;
import util.IdGenerator;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRideRequestTask implements Runnable {
    private final User user;
    private final BlockingQueue<RideRequest> rideRequestQueue;

    public UserRideRequestTask(User user, BlockingQueue<RideRequest> rideRequestQueue) {
        this.user = user;
        this.rideRequestQueue = rideRequestQueue;
    }

    @Override
    public void run() {
        try {
            RideRequest rideRequest;
            rideRequest = new RideRequest(IdGenerator.generateRequestId(), user, (Location) user.getCurrentLocation(), generateDestination());
            rideRequestQueue.put(rideRequest);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(
                    Thread.currentThread().getName()
                            + " interrupted while creating request."
            );
        }
    }

    private Location generateDestination() {
        double latitude = Math.random()*100;
        double longitude = Math.random()*100;
        return  new Location(longitude,latitude);
    }
}
