package com.bce.personas.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class User {
    @NotBlank
    @Size(min = 4, max = 25)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9.\\-_]+[a-zA-Z0-9]+$")
    String username;
    @NotBlank
    @Size(min = 8, max = 25)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]+$")
    String password;
}
