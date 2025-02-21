package it.epicode.final_project_s7_l5.security.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
