package org.bin.exception;

public class CalculationException extends RuntimeException {

  private static final long serialVersionUID = 6080014052733937123L;

  public CalculationException(String message) {
    super(message);
  }

  public CalculationException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
