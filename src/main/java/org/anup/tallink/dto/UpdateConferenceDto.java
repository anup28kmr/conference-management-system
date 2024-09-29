package org.anup.tallink.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateConferenceDto {

    @Min(1)
    private Long conferenceRoomId;

    @Future(message = "Conference date and time must be in the future")
    private LocalDateTime dateTime;

}
