package simulation;
import enums.DriverStatus;
import enums.Gender;
import enums.UserStatus;
import models.Driver;
import models.Location;
import models.RideRequest;
import models.User;
import models.Ride;
import service.DriverManager;
import service.RideManager;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<RideRequest> rideRequestQueue = new LinkedBlockingQueue<>();
        ConcurrentHashMap<Integer,Ride> rideConcurrentHashMap = new ConcurrentHashMap<>();
        DriverManager driverManager = new DriverManager();
        RideManager rideManager = new RideManager(rideConcurrentHashMap);
        for(int i=1;i<=500;i++) {
            Driver driver = new Driver(
                    i,
                    "Driver-" + i,
                    Gender.MALE,
                    new Location(
                            Math.random()*100,
                            Math.random()*100
                    ),
                    DriverStatus.AVAILABLE,
                    4
             );
            driverManager.addDriver(driver);
        }
        System.out.println(
                "Total Drivers Loaded : "
                        + driverManager.getTotalDrivers()
        );
        ExecutorService rideExecutor = Executors.newFixedThreadPool(100);
        for(int i=1;i<=5;i++) {
            Thread matcherThread = new Thread(
                    new RideMatcherTask(rideRequestQueue,driverManager,rideExecutor,rideManager),
                    "Matcher- " + i
            );
            matcherThread.start();
        }
        ExecutorService userExecutor = Executors.newFixedThreadPool(100);
        for(int i=1;i<=1000;i++) {
            User user = new User(
                    i,
                    "User- " + i,
                    Gender.MALE,
                    UserStatus.WAITING_FOR_DRIVER,
                    new Location(
                            Math.random()*100,
                            Math.random()*100
                    )
            );
            userExecutor.submit(
                    new UserRideRequestTask(
                            user,
                            rideRequestQueue
                    )
            );
        }
        userExecutor.shutdown();

        userExecutor.awaitTermination(
                1,
                TimeUnit.MINUTES
        );

        System.out.println(
                "All ride requests submitted."
        );

        Thread.sleep(300000);

        System.out.println(
                "Simulation Completed"
        );

        rideExecutor.shutdown();
    }
}

