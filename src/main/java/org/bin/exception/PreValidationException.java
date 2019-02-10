package org.bin.exception;

public class PreValidationException extends RuntimeException {

  private static final long serialVersionUID = -60862773881545471L;

  public PreValidationException(String message) {
    super(message);
  }

  public PreValidationException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
