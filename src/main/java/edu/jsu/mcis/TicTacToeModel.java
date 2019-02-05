package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */

       for (int i=0; i < getWidth();i++){
            for (int j=0; j < getWidth();j++){
                board[i][j] = Mark.EMPTY;
            }
       }
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        if (isValidSquare(row,col) == true && isSquareMarked(row,col) == false){
            if (xTurn == true){
                board[row][col] = Mark.X;
                xTurn = false;
            }
            else{
                board[row][col] = Mark.O;
                xTurn = true; 
            }
            return true;
        } 
        
        return false; // remove this line later!
        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        if (row >=0 && row < getWidth() && col >=0 && col < getWidth()){
            return true;
        }

        else { 
            return false;
        }
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        if (getMark(row,col).equals (Mark.EMPTY)){
            return false;
        }

        else{
            return true;
        }
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        return board[row][col]; 
            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        if(isMarkWin(Mark.X) == true){
            return Result.X;
        }
        else if(isMarkWin(Mark.O) == true){
            return Result.O;
        }
        else if(isTie() == true){
            return Result.TIE;
        }
        else{
            return Result.NONE;
        }
    }
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        for (int i = 0; i < getWidth(); i++){
            int colCounter = 0;
                for(int j = 0; j < getWidth(); j++){
                    if (getMark(i,j) != mark){
                        break;
                    }
                    else{
                        colCounter++;
                    }
                    if (colCounter == getWidth()){
                        return true;
                    }
                }   
            
    }

        for (int i = 0; i < getWidth(); i++){
            int rowCounter = 0;
                for(int j= 0; j < getWidth(); j++){
                    if (getMark(j,i) != mark){
                        break;
                    }
                    else{
                        rowCounter++;
                    }
                    if (rowCounter == getWidth()){
                        return true;
                    }
                }
            
        }

        int forDiag = 0;
        for (int i = 0; i < getWidth(); i++){
            if (getMark(i,i) != mark){
                break;
            }
            else{
                forDiag++;
            }
        }
        if(forDiag == getWidth()){
            return true;
        } 
        int backDiag = 0;
        int rowWorker = -1;
        int colWorker = getWidth();
        for (int i = 0; i < getWidth();i++){
            rowWorker += 1;
            colWorker -= 1;
            if(getMark(rowWorker,colWorker) != mark){
                    break;
            }
            else{
                backDiag++;
            } 
        }
        if(backDiag == getWidth()){
            return true;
        }
        return false;
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        int tieCounter = 0;
        for(int i = 0; i < getWidth(); i++){
            for(int j= 0; j < getWidth(); j++){
                if (getMark(i,j).equals(Mark.EMPTY)){
                    return false;
                }
                else{
                    tieCounter++;
                }
            }
        }
        if (tieCounter == (getWidth()*getWidth())){
            return true;
        }
        else{
            return false;
        }    
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        for(int i = 0; i < getWidth(); i++){
            output.append(i);
        }
        output.append("\n");
        int marginNumber = -1;
        for(int i = 0; i < getWidth(); i++){
            marginNumber++;
            output.append( marginNumber + " ");
            for(int j = 0; j < getWidth(); j++){
                output.append(board[i][j]);
            }
            output.append("\n");
        }
        return output.toString();
        
    }
    
}