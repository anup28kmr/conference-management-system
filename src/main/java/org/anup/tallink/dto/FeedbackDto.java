package org.anup.tallink.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FeedbackDto {

    @NotBlank
    private String content;
}
