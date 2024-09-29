package org.anup.tallink.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRoomStatusException extends RuntimeException {
    public InvalidRoomStatusException(String s) {
        super(s);
    }
}
