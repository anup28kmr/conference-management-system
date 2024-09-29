package org.anup.tallink.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidConferenceUpdateException.class)
    public ResponseEntity<String> handleInvalidConferenceUpdateException(InvalidConferenceUpdateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomCapacityExceededException.class)
    public ResponseEntity<String> handleRoomCapacityExceededException(RoomCapacityExceededException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConferenceRoomUnavailableException.class)
    public ResponseEntity<String> handleConferenceRoomUnavailableException(ConferenceRoomUnavailableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RegistrationAlreadyCanceledException.class)
    public ResponseEntity<String> handleRegistrationAlreadyCanceledException(RegistrationAlreadyCanceledException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParticipantFeedbackException.class)
    public ResponseEntity<String> handleInvalidParticipantFeedbackException(InvalidParticipantFeedbackException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomAlreadyExistsException.class)
    public ResponseEntity<String> handleRoomAlreadyExistsException(RoomAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRoomStatusException.class)
    public ResponseEntity<String> handleInvalidRoomStatusException(InvalidRoomStatusException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConferenceNotFoundException.class)
    public ResponseEntity<String> handleConferenceNotFoundException(ConferenceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
