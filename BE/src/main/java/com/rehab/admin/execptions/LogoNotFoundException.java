package com.rehab.admin.execptions;

public class LogoNotFoundException extends RuntimeException {
  public LogoNotFoundException(String message) {
    super(message);
  }
}
