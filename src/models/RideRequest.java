package models;
import enums.RequestStatus;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class RideRequest {
    private final int requestId;
    private final User user;
    private Location pickupLocation;
    private Location destinationLocation;
    private final LocalDateTime requestTime;
    private RequestStatus status;

    public RideRequest(int requestId, User user, Location pickupLocation, Location destinationLocation) {
        this.requestId = requestId;
        this.user = user;
        this.pickupLocation = pickupLocation;
        this.destinationLocation = destinationLocation;
        this.status = RequestStatus.PENDING;

        this.requestTime = LocalDateTime.now();
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public int getRequestId() {
        return requestId;
    }

    public User getUser() {
        return user;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void updatePickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
        System.out.println(
                "Pickup updated for request "
                        + requestId
        );
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public void updateDestinationLocation(Location destinationLocation) {
        this.destinationLocation = destinationLocation;
        System.out.println(
                "Destination updated for request "
                        + requestId
        );
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RideRequest{" +
                "requestId=" + requestId +
                ", user=" + user.getName() +
                ", pickupLocation=" + pickupLocation +
                ", destinationLocation=" +
                destinationLocation +
                ", requestTime=" + requestTime +
                ", status=" + status +
                '}';
    }
}
