package com.rehab.admin.execptions;

public class SendMailException extends RuntimeException {
  public SendMailException(String message) {
    super(message);
  }
}
