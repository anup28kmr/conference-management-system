package org.anup.tallink.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvailableConferenceDto {

    private Long conferenceId;
    private LocalDateTime dateTime;
    private String location;
    private long registeredParticipants;

    // Constructors, Getters, and Setters
}
