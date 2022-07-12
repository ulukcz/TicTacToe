package main.java.com.ulukbek.tictactoe;

import java.util.Random;

public class Field {
    public void printField(char[][] charField) {
        for (char[] chars : charField) {
            System.out.print("|");
            for (char aChar : chars) {
                System.out.print(aChar + "|");
            }
            System.out.println();
        }
    }
    public boolean addElementField(char[][] charField, int rawElement, int colElement, char element) {
        if (rawElement >= 0 && colElement >=0 && rawElement < 3 && colElement < 3) {
            if (charField[rawElement][colElement] == '-') {
                charField[rawElement][colElement] = element;
                return true;
            }
        }
        return false;
    }
    public void newFIeld(char[][] charField) {
        for (int i = 0; i < charField.length; i++) {
            for (int j = 0; j < charField[i].length; j++) {
                charField[i][j] = '-';
            }
        }
    }
    void randomAddChar(char[][] charField, char xoro) {
        Random random = new Random();
        while (true) {
            int col = (int) (random.nextDouble() * 3);
            int raw = (int) (random.nextDouble() * 3);
            if (charField[raw][col] == '-') {
                addElementField(charField, raw, col, xoro);
                break;
            }
        }
    }
    boolean isDraw(char[][] charField) {
        int result = 0;
        for (char[] chars : charField) {
            for (char aChar : chars) {
                if (aChar == '-') result++;
            }
        }
        return result == 0;
    }
}
