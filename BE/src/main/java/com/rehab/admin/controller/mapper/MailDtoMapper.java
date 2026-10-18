package com.rehab.admin.controller.mapper;

import com.rehab.admin.controller.dto.MailRequestDTO;
import com.rehab.admin.model.MailRequest;
import org.springframework.stereotype.Component;

@Component
public class MailDtoMapper {

    public MailRequest toMailEntity(MailRequestDTO requestDTO){
        return new MailRequest(
              requestDTO.sub(),
              requestDTO.msg(),
              requestDTO.role()
        );
    }
}
