package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowGameModel;
import view.RowGameGUI;


/**
 * Implementation of the 3 in a row game, using the Swing framework.
 */
public class RowGameControllerThreeInARow extends controller.RowGameController implements controller.RowGameRulesStrategy {

    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameControllerThreeInARow() {
        super();
    }

    public RowGameControllerThreeInARow(int rows, int columns) {
        super(rows, columns);
    }

    /**
     * Resets the game.
     *
     * @param gameModel
     */
    @Override
    public void reset(RowGameModel gameModel) {
        int rows = gameModel.getRows();
        int columns = gameModel.getColumns();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                gameModel.getBlocksData()[row][column].reset();
                // Enable the bottom row
                gameModel.getBlocksData()[row][column].setIsLegalMove(row == rows - 1);
            }
        }
        gameModel.setPlayer("1");
        gameModel.setMovesLeft(rows * columns);
        gameModel.setFinalResult(null);
    }

    /**
     * Sets the attributes of the model when a move function is called.
     *
     * @param player
     * @param playerContent
     * @param row
     * @param col
     */
    public void setPlayedMove(String player,
                               String playerContent, int row, int col) {
        if (gameModel.getBlocksData()[row][col].getIsLegalMove()) {
            gameModel.getBlocksData()[row][col].setContents(playerContent);
            gameModel.getBlocksData()[row][col].setIsLegalMove(false);
            if (row != 0) {
                gameModel.getBlocksData()[row - 1][col].setIsLegalMove(true);
            }
            gameModel.setPlayer(player);
        }
    }
}
