package com.bce.personas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDo {
    @JsonIgnore
    Long id;
    String name;
    String gender;
    Integer age;
    String identification;
    String direction;
    String phone;
    @JsonIgnore
    Long clientId;
    String password;
    Boolean state;
}
