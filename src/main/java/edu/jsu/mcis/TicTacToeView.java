package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToeView {

    private final Scanner keyboard;

    /* CONSTRUCTOR */

    public TicTacToeView() {

        /* Initialize scanner (for console keyboard) */

        keyboard = new Scanner(System.in);

    }

    public TicTacToeMove getNextMove(boolean isXTurn) {

        /* Prompt the player to enter the row and the column of their next move.
           Return as a TicTacToeMove object. */

        int row;
        int col;
        System.out.println("Please enter the row of your next move.");
        row = keyboard.nextInt();
        System.out.println("Please enter the column of your next move.");
        col = keyboard.nextInt();

        TicTacToeMove move = new TicTacToeMove(row,col);

        return move;
    }

    public void showInputError() {

        System.out.println("Entered location is invalid, already marked, or out of bounds.");

    }

    public void showResult(String r) {

        System.out.println(r + "!");

    }

    public void showBoard(String board) {

        System.out.println("\n\n" + board);

    }

}
