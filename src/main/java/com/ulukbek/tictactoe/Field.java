package main.java.com.ulukbek.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Field {
    // Печатаем в красивом виде наш двумерный массив
    public void printField(char[][] charField) {
        for (char[] chars : charField) {
            System.out.print("|");
            for (char aChar : chars) {
                System.out.print(aChar + "|");
            }
            System.out.println();
        }
    }
    // Добавляем крестик или нолик в двумерный массив
    // Предварительно проверяем на позицию и также содержание позиции
    public boolean addElementField(char[][] charField, int rawElement, int colElement, char element) {
        if (rawElement >= 0 && colElement >=0 && rawElement < 3 && colElement < 3) {
            if (charField[rawElement][colElement] == '-') {
                charField[rawElement][colElement] = element;
                return true;
            }
        }
        return false;
    }
    // Заполняем пустой массив для новой игры
    public void newFIeld(char[][] charField) {
        for (int i = 0; i < charField.length; i++) {
            for (int j = 0; j < charField[i].length; j++) {
                charField[i][j] = '-';
            }
        }
    }
    // Имитируем ход компьютера рандомным заполнением пустой ячейки
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
    // Имитируем умный ход компьютера, перекрывая ходы игрока или делая свой победный ход
    boolean botAddChar(char[][] charField, char xoroBot, char xoroGamer) {
        GameLogic logic = new GameLogic();
        for (int i = 0; i < charField.length; i++) {
            for (int j = 0; j < charField[i].length; j++) {
                char[][] tempBlock = copyTicTacToe(charField);      // Создаем массив для вероятного блокирования хода
                char[][] tempWin = copyTicTacToe(charField);        // Создаем массив для вероятного выигрыша
                if (charField[i][j] == '-') {
                    addElementField(tempBlock, i, j, xoroGamer);
                    addElementField(tempWin, i, j, xoroBot);
                    if (logic.isWinGame(tempBlock)) {
                        addElementField(charField, i, j, xoroBot);
                        return true;
                    } else if (logic.isWinGame(tempWin)) {
                        addElementField(charField, i, j, xoroBot);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // Проверяем, не ничья ли
    boolean isDraw(char[][] charField) {
        int result = 0;
        for (char[] chars : charField) {
            for (char aChar : chars) {
                if (aChar == '-') result++;
            }
        }
        return result == 0;
    }
    // Заполняем ячейку, данными введенными пользователем
    void gamerAddPosition(Scanner input, char xoro, char[][] tictactoe) {
        boolean rightPosition;
        do {
            System.out.println("Row position ?");
            int raw = input.nextInt();
            System.out.println("Column position ?");
            int col = input.nextInt();
            rightPosition = addElementField(tictactoe, raw, col, xoro);
        } while (!rightPosition);
    }
    // копируем поле игры для будущего вычисления потенциальных ходов
    char[][] copyTicTacToe(char[][] chars) {
        char[][] result = new char[3][3];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                result[i][j] = chars[i][j];
            }
        }
        return result;
    }
    // Ход компьютера
    void botAddPosition(char[][] charField, char xoroBot, char xoroGamer) {
        if (!botAddChar(charField, xoroBot, xoroGamer))
            randomAddChar(charField, xoroBot);
    }
}
