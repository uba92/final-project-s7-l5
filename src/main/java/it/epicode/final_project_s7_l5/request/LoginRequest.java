package it.epicode.final_project_s7_l5.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
