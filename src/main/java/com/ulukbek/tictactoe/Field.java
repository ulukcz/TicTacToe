package com.ulukbek.tictactoe;

import java.util.Arrays;

public class Field {
  private final String[] values = new String[9];

  public String[] getValues() {
    return values;
  }

  public void setValue(int index, String value) {
      if (index >= 0 && index < 9) {
        this.values[index] = value;
      }
  }

  public void showField() {
    for (int i = 0; i < 3; i++) {
      for (int j = i * 3; j < (i + 1) * 3; j++) {
        System.out.print("|");
        if (values[j] == null)
          System.out.print("-" + "|");
        else
          System.out.print(values[j] + "|");
      }
      System.out.println();
    }
  }

}
