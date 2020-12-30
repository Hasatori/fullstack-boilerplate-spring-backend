package com.example.springsocial.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ForgottenPasswordRequest {

    @NotBlank(message = "{email.notEmpty}")
    @Email
    private String email;

}