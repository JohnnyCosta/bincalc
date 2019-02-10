package org.bin.exception;

public class InitNullException extends RuntimeException {

  private static final long serialVersionUID = 663169022447947905L;

  public InitNullException(String message) {
    super(message);
  }

  public InitNullException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
