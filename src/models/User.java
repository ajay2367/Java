package models;
import enums.Gender;
import enums.UserStatus;

import javax.xml.stream.Location;

public class User {
    private final int userId;
    private final String name;
    private final Gender gender;
    private UserStatus status;
    private Location currentLocation;

    public User(int userId, String name, Gender gender, UserStatus status, Location currentLocation) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.currentLocation = currentLocation;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", currentLocation=" + currentLocation +
                ", status=" + status +
                '}';
    }
}
