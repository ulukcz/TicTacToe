package main.java.com.ulukbek.tictactoe;

import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Start new game? (y/n)");
        endgame: while (true) {
            String newGame = input.nextLine();
            if (newGame.equals("n"))
                break;
            else if(newGame.equals("y")) {
                Field newGameField = new Field();
                GameLogic gameLogic = new GameLogic();
                char[][] tictactoe = new char[3][3];                        // Создаем новый массив для новой игры
                newGameField.newFIeld(tictactoe);                           // Заполняем его элементами "-"
                System.out.println("'x' or 'o'?");
                char xoroChar = '-';
                char xoroBot = '-';
                while (xoroChar == '-') {                                   // Узнаем, кто играет за крестик или нолик
                    String xoro = input.nextLine();
                    if (xoro.equals("x") || xoro.equals("o")) {
                        xoroChar = xoro.charAt(0);
                        if (xoroChar == 'x') xoroBot = 'o';
                        else xoroBot = 'x';
                    } else
                        System.out.println("Please type 'x' or 'o'!");
                }
                while (!gameLogic.isWinGame(tictactoe)) {                   // Играем до тех пор, пока кто-то не выиграет
                    if (xoroChar == 'x') {                                  // Логика игры, если первым ходит игрок
                        newGameField.printField(tictactoe);
                        newGameField.gamerAddPosition(input, xoroChar, tictactoe);
                        if (gameLogic.isWinGame(tictactoe)) {
                            newGameField.printField(tictactoe);
                            System.out.println("Congratulation! You win! You  winning with '" + xoroChar + "'");
                            continue endgame;
                        }
                        if(newGameField.isDraw(tictactoe)) {                // Если ничья, то заканчиваем игру
                            newGameField.printField(tictactoe);
                            System.out.println("This is Draw!");
                            continue endgame;
                        }
                        newGameField.botAddPosition(tictactoe, xoroBot, xoroChar);
                        if (gameLogic.isWinGame(tictactoe)) {
                            newGameField.printField(tictactoe);
                            System.out.println("Sorry! Computer win! Computer win with '" + xoroBot + "'");
                            continue endgame;
                        }
                    } else {                                                 // Логика игры, если первым ходит компьютер
                        newGameField.botAddPosition(tictactoe, xoroBot, xoroChar);
                        newGameField.printField(tictactoe);
                        if (gameLogic.isWinGame(tictactoe)) {
                            System.out.println("Sorry! Computer win! Computer win with '" + xoroBot + "'");
                            continue endgame;
                        }
                        if(newGameField.isDraw(tictactoe)) {
                            System.out.println("This is Draw!");
                            continue endgame;
                        }
                        newGameField.gamerAddPosition(input, xoroChar, tictactoe);
                        if (gameLogic.isWinGame(tictactoe)) {
                            newGameField.printField(tictactoe);
                            System.out.println("Congratulation! You win! You  winning with '" + xoroChar + "'");
                            continue endgame;
                        }
                    }

                }
            } else {
                System.out.println("For start, please type 'y'. To stop game type 'n'");
            }
        }
    }
}
