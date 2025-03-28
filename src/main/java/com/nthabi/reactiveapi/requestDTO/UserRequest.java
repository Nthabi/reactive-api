package com.nthabi.reactiveapi.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Firstname cannot be empty")
    @Size(min = 2, max = 50, message = "Firstname must have atleast 2 characters and at most 50 characters")
    private String firstname;

    @NotBlank(message = "Lastname cannot be empty")
    @Size(min = 2, max = 50, message = "Lastname must have atleast 2 characters and at most 50 characters")
    private String lastname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotBlank(message = "Please enter password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Password must contain at least one letter, one number, and be at least 8 characters long")
    private String password;
}
