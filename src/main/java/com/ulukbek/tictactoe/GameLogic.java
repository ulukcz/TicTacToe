package com.ulukbek.tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {
    private final String USER_FIGURE = "X";
    private final String PC_FIGURE = "O";
    private final Scanner scanner = new Scanner(System.in);
    private final Field field = new Field();

    private final Random random = new Random();

    public void start(){
        while (!checkWinner()) {
            getUserInput();
            if (checkWinner()) {
                field.showField();
                System.out.println("Game is end!");
            } else
                generatePCInput();
        }
    }

    private boolean validateInput(Integer index) {
        return index < 0 || index >= 9 || field.getValues()[index] != null;
    }

    public void getUserInput() {
        field.showField();
        System.out.println("Enter index: ");
        int index = scanner.nextInt();
        while (validateInput(index)) {
            System.out.println("Enter index: ");
            index = scanner.nextInt();
        }
        field.setValue(index, USER_FIGURE);
    }

    public void generatePCInput() {
        int index = random.nextInt(9);
        while (validateInput(index)) {
            index = random.nextInt(9);
        }
        field.setValue(index, PC_FIGURE);
    }

    public boolean checkWinner () {
        String[] tempField = field.getValues();
        String[] xLine = {"X", "X", "X"};
        String[] oLine = {"O", "O", "O"};
        for (int i = 0; i < 3; i++) {
            String[] lineRow = {tempField[i * 3], tempField[i * 3 + 1], tempField[i * 3 + 2]};
            if (Arrays.equals(xLine, lineRow) || Arrays.equals(oLine, lineRow))
                return true;
            String[] lineCol = {tempField[i], tempField[i + 3], tempField[i + 6]};
            if (Arrays.equals(xLine, lineCol) || Arrays.equals(oLine, lineCol))
                return true;
        }
        String[] lineDiag1 = {tempField[0], tempField[4], tempField[8]};
        String[] lineDiag2 = {tempField[2], tempField[4], tempField[6]};
        if (Arrays.equals(xLine, lineDiag1) || Arrays.equals(oLine, lineDiag1))
            return true;
        if (Arrays.equals(xLine, lineDiag2) || Arrays.equals(oLine, lineDiag2))
            return true;
        return checkDraw();
    }
    public boolean checkDraw() {
        int count = 0;
        for (String s: field.getValues()) {
            if (s != null) count++;
        }
        return count == field.getValues().length;
    }
}
