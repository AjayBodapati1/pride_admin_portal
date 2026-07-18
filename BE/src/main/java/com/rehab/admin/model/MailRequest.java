package com.rehab.admin.model;

import lombok.Data;

@Data
public class MailRequest {
    private String sub;
    private String msg;
    private String role;
}
