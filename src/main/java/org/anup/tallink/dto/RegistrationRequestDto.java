package org.anup.tallink.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.anup.tallink.entity.Gender;

import java.time.LocalDate;

@Data
public class RegistrationRequestDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

}
