package wazzle.view.controller;

public enum QWERTYKeyboard {
  UPPER_ROW("qwertyuiop"),
  MIDDLE_ROW("asdfghjkl"),
  LOWER_ROW("zxcvbnm");

  private final String row;

  QWERTYKeyboard(String string) {
    this.row = string;
  }

  public String getKeyboardRow() {
    return row;
  }
}
