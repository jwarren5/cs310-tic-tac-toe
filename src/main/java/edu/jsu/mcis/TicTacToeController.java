package edu.jsu.mcis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;


public class TicTacToeController implements ActionListener{

    private final TicTacToeModel model;
    private final TicTacToeView view;

    /* CONSTRUCTOR */

    public TicTacToeController(int width) {

        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this,width);

    }

    public String getMarkAsString(int row, int col) {
        return (model.getMark(row, col).toString());
      }
  
      public TicTacToeView getView() {
          return view;
      }
  
      @Override
      public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
  
        if (source instanceof JButton) {
  
            JButton button = (JButton)source;
  
            String bName = button.getName();
  
            int nRow = 0;
            int nCol = 0;
            Character chRow;
            Character chCol;
  
            chRow = bName.charAt(6);
            chCol = bName.charAt(7);
  
            nRow = Character.getNumericValue(chRow);
            nCol = Character.getNumericValue(chCol);
  
            view.updateSquares(button, model.isXTurn(), model.makeMark(nRow,nCol));
  
            view.showResult(model.getResult().toString());
      }
  
    }
  }