package com.tutorialsninja.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationForm {

    @JsonProperty("FirstName")
    String firstName;

    @JsonProperty("LastName")
    String lastName;

    @JsonProperty("Email")
    String email;

    @JsonProperty("Telephone")
    String telephone;

    @JsonProperty("Password")
    String password;

    public String[] toArray() {
        return new String[]{firstName, lastName, email, telephone, password};
    }
}
