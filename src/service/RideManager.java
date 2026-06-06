package service;
import models.Ride;
import java.util.concurrent.ConcurrentHashMap;

public class RideManager {
    private final ConcurrentHashMap<Integer,Ride> rides;

    public RideManager(ConcurrentHashMap<Integer, Ride> rides) {
        this.rides = rides;
    }

    public void addRide(Ride ride) {
        rides.put(
                ride.getRideId(),
                ride
        );
    }

    public Ride getRide(int rideId) {
        return rides.get(rideId);
    }

    public void removeRide(int rideId) {
        rides.remove(rideId);
    }

    public int getTotalRides() {
        return rides.size();
    }

    public ConcurrentHashMap<Integer,Ride> getRides() {
        return rides;
    }
}
