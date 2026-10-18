package com.rehab.admin.controller.mapper;

import com.rehab.admin.controller.dto.LoginCredentialsDTO;
import com.rehab.admin.model.LoginCredentials;
import org.springframework.stereotype.Component;

@Component
public class CredentialsDtoMapper {
    public LoginCredentials toCredentialsEntity(LoginCredentialsDTO credentialsDTO){
        return new LoginCredentials(
                credentialsDTO.email(),
                credentialsDTO.password()
        );
    }
}
