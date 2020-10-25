package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.RowGameModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class RowGameBoardView implements RowGameView {
    private JButton[][] blocks;
    private JPanel gamePanel = new JPanel(new FlowLayout());


    public RowGameBoardView(controller.RowGameController gameController) {
        super();

        int rows = gameController.getModel().getRows();
        int columns = gameController.getModel().getColumns();
        this.blocks = new JButton[rows][columns];
        JPanel game = new JPanel(new GridLayout(rows, columns));
        gamePanel.add(game, BorderLayout.CENTER);

        gameController.getModel().addPropertyChangeListener(new
           PropertyChangeListener() {
               @Override
               public void propertyChange(PropertyChangeEvent e) {
                   update(gameController.getModel());
               }
           });

        // Initialize a JButton for each cell of the rowsxcolumns game board.
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75, 75));
                game.add(blocks[row][column]);
                int finalRow = row;
                int finalColumn = column;
                blocks[row][column].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameController.executeMove((JButton) e.getSource(), finalRow, finalColumn);
                    }
                });
            }
        }
    }

    /**
     * Updates the game view after the game model
     * changes state.
     *
     * @param gameModel The current game model
     */
    public void update(RowGameModel gameModel) {
        for (int row = 0; row < gameModel.getRows(); row++) {
            for (int column = 0; column < gameModel.getColumns(); column++) {
                this.updateBlock(gameModel, row, column);
            } // end for col
        } // end for row
    }

    /**
     * Updates the block view at the given row and column
     * after the game model changes state.
     *
     * @param gameModel The game model
     * @param row       The row that contains the block
     * @param col    The column that contains the block
     */
    protected void updateBlock(RowGameModel gameModel, int row, int col) {
        blocks[row][col].setText(gameModel.getBlocksData()[row][col].getContents());
        blocks[row][col].setEnabled(gameModel.getBlocksData()[row][col].getIsLegalMove());
    }

    public JButton[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(JButton[][] blocks) {
        this.blocks = blocks;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
