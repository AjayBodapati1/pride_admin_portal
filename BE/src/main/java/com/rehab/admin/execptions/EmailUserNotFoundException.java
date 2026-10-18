package com.rehab.admin.execptions;

public class EmailUserNotFoundException extends RuntimeException {
  public EmailUserNotFoundException(String message) {
    super(message);
  }
}
