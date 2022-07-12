package main.java.com.ulukbek.tictactoe;

public class GameLogic {
    boolean equalCharsForWin(char[] arr) {
        int x = 0;
        int o = 0;

        for (char ch: arr) {
            if (ch == 'x') x++;
            if (ch == 'o') o++;
        }
        return x == 3 || o == 3;
    }
    boolean isWinGame(char[][] field) {
        for (char[] rowChars: field) {
            if (equalCharsForWin(rowChars)) return true;
        }
        for (int i = 0; i < 3; i++) {
            char[] colChars  = new char[3];
            for (int j = 0; j < 3; j++) {
                colChars[j] = field[j][i];
            }
            if(equalCharsForWin(colChars)) return true;
        }
        char[] diagCharsFirst = new char[3];
        char[] diagCharsSecond = new char[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) diagCharsFirst[j] = field[i][j];
                if (i + j == 2) diagCharsSecond[j] = field[i][j];
            }
        }
        return equalCharsForWin(diagCharsFirst) || equalCharsForWin(diagCharsSecond);
    }
}
