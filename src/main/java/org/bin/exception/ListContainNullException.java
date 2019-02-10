package org.bin.exception;

public class ListContainNullException extends RuntimeException {

  private static final long serialVersionUID = 6542359460879092445L;

  public ListContainNullException(String message) {
    super(message);
  }

  public ListContainNullException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
