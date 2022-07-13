package main.java.com.ulukbek.tictactoe;

public class GameLogic {
    // Узнаем состоит ли массив полностью из крестиков или ноликов
    boolean equalCharsForWin(char[] arr) {
        int x = 0;
        int o = 0;

        for (char ch: arr) {
            if (ch == 'x') x++;
            if (ch == 'o') o++;
        }
        return x == 3 || o == 3;
    }
    // Явлется ли наш двумерный массив выигрышным,
    // то есть содержит ли три в ряд крестиков или ноликов
    boolean isWinGame(char[][] field) {
        // Проверяем по горизонтали
        for (char[] rowChars: field) {
            if (equalCharsForWin(rowChars)) return true;
        }
        // Проверяем по вертикали
        for (int i = 0; i < 3; i++) {
            char[] colChars  = new char[3];
            for (int j = 0; j < 3; j++) {
                colChars[j] = field[j][i];
            }
            if(equalCharsForWin(colChars)) return true;
        }
        // Проверяем по диагонали
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
