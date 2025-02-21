package it.epicode.final_project_s7_l5.request;

import it.epicode.final_project_s7_l5.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Set<Role> roles;
}
