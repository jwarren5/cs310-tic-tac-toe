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
        
        boolean nextMove = false;
        TicTacToeModel model = new TicTacToeModel();
            if (isXTurn == true){
                System.out.println("\nPlayer 1 (X) Move: ");
            }
            else{
                System.out.println("\nPlayer 2 (O) Move: ");
            }
            int row = keyboard.nextInt();
            int col = keyboard.nextInt();
            if (0 > row || row > model.getWidth()){
                showInputError();
                return getNextMove(isXTurn);
            }
            else if (0 > col || col > model.getWidth()){
                showInputError();
                return getNextMove(isXTurn);
            }
            else{
                TicTacToeMove playerInput = new TicTacToeMove(row,col); 
                return playerInput;
            }
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
