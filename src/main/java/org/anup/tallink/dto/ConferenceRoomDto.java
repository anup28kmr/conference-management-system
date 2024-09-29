package org.anup.tallink.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.anup.tallink.entity.RoomStatus;

@Data
public class ConferenceRoomDto {
    @NotBlank(message = "Room name is mandatory")
    private String name;

    @NotBlank(message = "Room location is mandatory")
    private String location;

    @NotNull(message = "Room status is mandatory")
    private RoomStatus status;

    @Min(value = 1, message = "Max capacity must be greater than zero")
    private Integer maxCapacity;

    // Getters and Setters
}
