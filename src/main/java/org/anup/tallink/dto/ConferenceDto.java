package org.anup.tallink.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConferenceDto {
    @NotNull(message = "Conference room ID is mandatory")
    private Long conferenceRoomId;

    @NotNull(message = "Conference date and time is mandatory")
    @Future(message = "Conference date and time must be in the future")
    private LocalDateTime dateTime;

    @Min(value = 1, message = "Max participants must be greater than zero")
    private Integer maxParticipants;

}
