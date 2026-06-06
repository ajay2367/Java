package util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final AtomicInteger
            requestIdGenerator =
            new AtomicInteger(1);

    private static final AtomicInteger rideIdGenerator =
            new AtomicInteger(1);

    public static int generateRequestId() {

        return requestIdGenerator
                .getAndIncrement();
    }

    public static int generateRideId() {
        return rideIdGenerator.getAndIncrement();
    }
}