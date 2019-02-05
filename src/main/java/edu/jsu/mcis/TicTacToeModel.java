
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

        for (int i = 0; i < width; ++i){
          for(int j =0; j < width; ++j){
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

        boolean madeMark = false;

        if(this.isValidSquare(row, col) == true && this.isSquareMarked(row,col) == false){
          if(this.xTurn == true){
            board[row][col] = Mark.X;
            madeMark = true;
          }
          else{
            board[row][col] = Mark.O;
            madeMark = true;
          }
          xTurn = !xTurn;
        }
        else{
          madeMark = false;
        }

        return madeMark;

    }

    private boolean isValidSquare(int row, int col) {

        /* Return TRUE if the specified location is within the bounds of the board */

        boolean valid = false;
        if((row > width - 1) || (col > width - 1) || (row < 0) || (col < 0) ){
          valid = false;
        }
        else{
          valid = true;
        }

        return valid; // remove this line later!

    }

    private boolean isSquareMarked(int row, int col) {

        /* Return TRUE if the square at specified location is marked */
        boolean marked = true;
        if(board[row][col] == Mark.EMPTY){
          marked = false;
        }

        return marked;

    }

    public Mark getMark(int row, int col) {

        /* Return the mark from the square at the specified location */

        return board[row][col];

    }

    public Result getResult() {

        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        Result result = Result.NONE;
        Mark mark1 = Mark.X;
        Mark mark2 = Mark.O;

        boolean xWin = this.isMarkWin(mark1);
        boolean yWin = this.isMarkWin(mark2);
        boolean tie = this.isTie();

        if(xWin == true){
          result = Result.X;
        }
        else if(yWin == true){
          result = Result.O;
        }
        else if (tie == true){
          result = Result.TIE;
        }
        else{
          result = Result.NONE;
        }


        return result;

    }

    private boolean isMarkWin(Mark mark) {

        /* Check the squares of the board to see if the specified mark is the
           winner */

           boolean win = false;
           int compareCounter = 0;
           Mark compareMark = mark;

           for(int row = 0; row < width; ++row){
               compareCounter = 0;
               for(int col = 0; col < width; ++col){
                   Mark boardMark = this.getMark(row, col);

                   if(boardMark.equals(compareMark)){
                       ++compareCounter;
                       if(compareCounter == width){
                           win = true;
                       }
                   }
                   else if(boardMark != compareMark){
                       compareCounter = 0;
                   }

               }
           }
           compareCounter = 0;

           for(int col = 0; col < width; ++col){
               compareCounter = 0;
               for(int row = 0; row < width; ++row){
                   Mark boardMark = this.getMark(row, col);

                   if(boardMark.equals(compareMark)){
                       ++compareCounter;
                       if(compareCounter == width){
                           win = true;
                       }
                   }
                   else if(boardMark != compareMark){
                       compareCounter = 0;
                   }

               }
           }
           compareCounter = 0;

           for(int i = 0; i < width; ++i){
               if(board[i][i].equals(compareMark)){
                   ++compareCounter;
                   if(compareCounter == width){
                       win = true;
                   }
               }

                   else{
                       compareCounter = 0;
                   }

           }
           compareCounter = 0;

           for(int j = width - 1; j >= 0; --j){
               if(board[j][width - j - 1].equals(compareMark)){
                   ++compareCounter;
                   if(compareCounter == width){
                       win = true;
                   }
               }
               else{
                   compareCounter = 0;
               }
           }
           compareCounter = 0;


        return win;

    }

    private boolean isTie() {

        /* Check the squares of the board to see if the game is a tie */

        boolean tie = false;
        int emptyCounter = 0;
        for(int row = 0; row < width; ++row){
            for(int col = 0; col < width; ++col){
                if(board[row][col].equals(Mark.EMPTY)){
                    ++emptyCounter;
                }
            }
        }

        if(emptyCounter == 0){
            tie = true;
        }

        return tie;

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
        for(int col = 0; col < width; ++col){
          output.append(col);
        }
        output.append("\n\n");

        for(int i = 0; i < width; ++i){
          output.append(i);
          for(int j = 0; j < width; ++j){
            output.append(Mark.EMPTY);
          }
        }

        output.append("\n\n\n\n\n");

        return output.toString();

    }

}