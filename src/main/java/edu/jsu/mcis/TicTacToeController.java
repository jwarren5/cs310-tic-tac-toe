package edu.jsu.mcis;

public class TicTacToeController {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    private TicTacToeMove move;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView();
        
    }

    public void start() {
    
        /* MAIN LOOP (repeats until game is over) */

        /* Display the board using the View's "showBoard()", then use
           "getNextMove()" to get the next move from the player.  Enter
           the move (using the Model's "makeMark()", or display an error
           using the View's "showInputError()" if the move is invalid. */

        boolean gameOver = false;
        while (gameOver == false){
            view.showBoard(model.toString());
            move = view.getNextMove(model.isXTurn());
            model.makeMark(move.getRow(),move.getCol());
            if (model.getResult().equals(TicTacToeModel.Result.X)||model.getResult().equals(TicTacToeModel.Result.O)){
                gameOver = true;
            }
        }    
        /* After the game is over, show the final board and the winner */

        view.showBoard(model.toString());

        view.showResult(model.getResult().toString());
        
    }

}
