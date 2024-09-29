package org.anup.tallink.dto;

import lombok.Data;

@Data
public class RegistrationResponseDto {

    private String participantCode;
    private String message;

    public RegistrationResponseDto(String participantCode, String message) {
        this.participantCode = participantCode;
        this.message = message;
    }

    // Getters and Setters
}
