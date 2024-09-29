package org.anup.tallink.exceptions;

public class RoomCapacityExceededException extends RuntimeException {

    public RoomCapacityExceededException(String message) {
        super(message);
    }
}
