package org.game;

import java.util.Scanner;
import java.util.Random;

public class Game {

    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';

    private char[] board = new char[BOARD_SIZE];
    private boolean isGameOver = false;
    private int winner = 0;  // 0 - game ongoing, 1 - player won, 2 - computer won, 3 - draw

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    public void play() {
        initializeBoard();
        displayWelcomeMessage();

        while (!isGameOver) {
            displayBoard();
            playerTurn();
            checkWinner();
            if (!isGameOver) {
                computerTurn();
                checkWinner();
            }
        }

        displayBoard();
        displayGameResult();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY_CELL;
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("Enter box number to select. Enjoy!\n");
    }

    private void displayBoard() {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private void playerTurn() {
        Scanner scan = new Scanner(System.in);
        int input;

        while (true) {
            System.out.print("Your move (1-9): ");
            input = scan.nextInt() - 1;
            if (input >= 0 && input < BOARD_SIZE && board[input] == EMPTY_CELL) {
                board[input] = PLAYER_MARK;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private void computerTurn() {
        Random rand = new Random();
        int move;

        while (true) {
            move = rand.nextInt(BOARD_SIZE);
            if (board[move] == EMPTY_CELL) {
                board[move] = COMPUTER_MARK;
                break;
            }
        }
    }

    private void checkWinner() {
        if (isWinningCombination(PLAYER_MARK)) {
            winner = 1;
            isGameOver = true;
        } else if (isWinningCombination(COMPUTER_MARK)) {
            winner = 2;
            isGameOver = true;
        } else if (isBoardFull()) {
            winner = 3;
            isGameOver = true;
        }
    }

    private boolean isWinningCombination(char mark) {
        return (board[0] == mark && board[1] == mark && board[2] == mark) ||
                (board[3] == mark && board[4] == mark && board[5] == mark) ||
                (board[6] == mark && board[7] == mark && board[8] == mark) ||
                (board[0] == mark && board[3] == mark && board[6] == mark) ||
                (board[1] == mark && board[4] == mark && board[7] == mark) ||
                (board[2] == mark && board[5] == mark && board[8] == mark) ||
                (board[0] == mark && board[4] == mark && board[8] == mark) ||
                (board[2] == mark && board[4] == mark && board[6] == mark);
    }

    private boolean isBoardFull() {
        for (char cell : board) {
            if (cell == EMPTY_CELL) {
                return false;
            }
        }
        return true;
    }

    private void displayGameResult() {
        switch (winner) {
            case 1:
                System.out.println("You won the game! Thanks for playing!");
                break;
            case 2:
                System.out.println("You lost the game! Thanks for playing!");
                break;
            case 3:
                System.out.println("It's a draw! Thanks for playing!");
                break;
        }
    }
}
