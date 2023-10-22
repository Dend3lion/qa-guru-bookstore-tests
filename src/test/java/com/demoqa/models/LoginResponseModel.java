package com.demoqa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModel {
    String userId;
    String username;
    String password;
    @JsonProperty("created_date")
    String createdDate;
    String expires;
    String token;
    String isActive;
}
