package com.math.game.Exeption;

public class UserAlreadyExistsExeption extends RuntimeException {
  public UserAlreadyExistsExeption(String message) {
    super(message);
  }
}
