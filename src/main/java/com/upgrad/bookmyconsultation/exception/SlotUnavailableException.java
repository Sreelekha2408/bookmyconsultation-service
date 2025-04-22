package com.upgrad.bookmyconsultation.exception;

public class SlotUnavailableException extends RuntimeException {


  public Object getDescription() {
    return "Slot is unavailable";
  }
}
