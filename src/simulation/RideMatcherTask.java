package simulation;
import enums.RideStatus;
import models.Ride;
import models.User;
import models.Driver;
import models.RideRequest;
import service.DriverManager;
import service.RideManager;
import util.IdGenerator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class RideMatcherTask implements Runnable {
    private final BlockingQueue<RideRequest> rideRequestQueue;
    private final DriverManager driverManager;
    private final ExecutorService rideExecutor;
    private final RideManager rideManager;

    public RideMatcherTask(BlockingQueue<RideRequest> rideRequestQueue, DriverManager driverManager, ExecutorService rideExecutor, RideManager rideManager) {
        this.rideRequestQueue = rideRequestQueue;
        this.driverManager = driverManager;
        this.rideExecutor = rideExecutor;
        this.rideManager = rideManager;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                RideRequest request = rideRequestQueue.take();
                Driver driver = driverManager.assignAvailableDriver();
                if(driver == null) {
                    rideRequestQueue.put(request);
                    Thread.sleep(100);
                    continue;
                }
                Ride ride = new Ride(IdGenerator.generateRideId(),request,driver);
                System.out.println(
                        Thread.currentThread().getName()
                                + " matched Request "
                                + request.getRequestId()
                                + " -> Ride "
                                + ride.getRideId()
                                + " -> Driver "
                                + driver.getDriverId()
                );
                ride.updateStatus(
                        RideStatus.DRIVER_ASSIGNED
                );
                rideManager.addRide(ride);
                rideExecutor.submit(
                        new RideTask(ride,driverManager)
                );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
