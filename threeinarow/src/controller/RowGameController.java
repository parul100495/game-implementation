package controller;

import model.RowGameModel;
import view.RowGameGUI;

import javax.swing.*;

/**
 * This class is an abstract class which is extended by RowGameControllerThreeInARow and
 * RowGameControllerTicTacToe.
 */
public abstract class RowGameController implements controller.RowGameRulesStrategy {
    private static final String GAME_END_NOWINNER = "Game ends in a draw";

    protected RowGameModel gameModel;
    protected RowGameGUI gameView;

    public RowGameController() {
        gameModel = new RowGameModel();
        gameView = new RowGameGUI(this);

        reset(gameModel);
    }

    /**
     * Dynamically create grid of size rows * columns
     *
     * @param rows
     * @param columns
     */
    public RowGameController(int rows, int columns) {
        gameModel = new RowGameModel(rows, columns);
        gameView = new RowGameGUI(this);

        reset(gameModel);
    }

    /**
     * Initialize GUI
     */
    @Override
    public void startUp() {
        gameView.getGui().setVisible(true);
    }

    /**
     * Executes the move function
     *
     * @param block
     * @param row
     * @param col
     */
    public void executeMove(JButton block, int row, int col) {
        move(this.gameModel, row, col);
    }

    @Override
    public void reset(RowGameModel gameModel) {
    }

    /**
     * Implements the move function.
     *
     * @param gameModel
     * @param row
     * @param col
     */
    @Override
    public void move(RowGameModel gameModel, int row, int col) {
        gameModel.setMovesLeft(gameModel.getMovesLeft() - 1);

        String player = gameModel.getPlayer();
        int movesLeft = gameModel.getMovesLeft();
        int rows = gameModel.getRows();
        int columns = gameModel.getColumns();
        if (player.equals("1")) {
            // Check whether player 1 won
            setPlayedMove("2", "X", row, col);
            if (movesLeft < rows * columns - 2) {
                if (isWin(this.gameModel, row, col)) {
                    endGame();
                    gameModel.setFinalResult("Player 1 wins!");
                } else if (isTie(gameModel)) {
                    gameModel.setFinalResult(GAME_END_NOWINNER);
                }
            }
        } else {
            // Check whether player 2 won
            setPlayedMove( "1", "O", row, col);
            if (movesLeft < rows * columns - 2) {
                if (isWin(this.gameModel, row, col)) {
                    endGame();
                    gameModel.setFinalResult("Player 2 wins!");
                } else if (isTie(gameModel)) {
                    gameModel.setFinalResult(GAME_END_NOWINNER);
                }
            }
        }
    }

    public abstract void setPlayedMove(String player,
                                       String playerContent, int row, int col);

    public void endGame() {
        for (int row = 0; row < gameModel.getRows(); row++) {
            for (int column = 0; column < gameModel.getColumns(); column++) {
                this.gameModel.getBlocksData()[row][column].setIsLegalMove(false);
            }
        }

    }

    /**
     * Returns whether the player is a winner or not.
     *
     * @param gameModel
     * @param row
     * @param col
     * @return boolean value
     */
    @Override
    public boolean isWin(RowGameModel gameModel, int row, int col) {
        String playerContent = gameModel.getBlocksData()[row][col].getContents();
        if (isRowSuccess(row, playerContent) ||
                isColSuccess(col, playerContent) ||
                isDiagonalSuccess(row, col, playerContent) ||
                isAntiDiagonalSuccess(row, col, playerContent))
            return true;
        return false;
    }

    /**
     * Whether the row was formed by the player or not.
     *
     * @param row
     * @param playerContent
     * @return boolean value
     */
    private boolean isRowSuccess(int row, String playerContent) {
        for (int col = 0; col < gameModel.getColumns(); col++) {
            if (gameModel.getBlocksData()[row][col].getContents() != playerContent)
                return false;
        }
        return true;
    }

    /**
     * Whether the column was formed by the player or not.
     *
     * @param col
     * @param playerContent
     * @return boolean value
     */
    private boolean isColSuccess(int col, String playerContent) {
        for (int row = 0; row < gameModel.getRows(); row++) {
            if (gameModel.getBlocksData()[row][col].getContents() != playerContent)
                return false;
        }
        return true;
    }

    /**
     * Whether the diagonal was formed by the player or not.
     *
     * @param row
     * @param col
     * @param playerContent
     * @return boolean value
     */
    private boolean isDiagonalSuccess(int row, int col, String playerContent) {
        if (row == col) {
            for (int i = 0; i < gameModel.getRows(); i++) {
                if (gameModel.getBlocksData()[i][i].getContents() != playerContent)
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Whether the anti-diagonal was formed by the player or not.
     *
     * @param row
     * @param col
     * @param playerContent
     * @return boolean value
     */
    private boolean isAntiDiagonalSuccess(int row, int col, String playerContent) {
        if (row + col == gameModel.getRows() - 1) {
            for (int i = 0; i < gameModel.getRows(); i++) {
                if (gameModel.getBlocksData()[i][gameModel.getRows() - 1 - i].getContents() != playerContent)
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Whether the game tied or not
     *
     * @param gameModel
     * @return boolean
     */
    @Override
    public boolean isTie(RowGameModel gameModel) {
        if (gameModel.getMovesLeft() == 0) {
            return true;
        }
        return false;
    }

    public RowGameModel getModel() {
        return this.gameModel;
    }

    public RowGameGUI getView() {
        return this.gameView;
    }

    public RowGameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(RowGameModel gameModel) {
        this.gameModel = gameModel;
    }

    public RowGameGUI getGameView() {
        return gameView;
    }

    public void setGameView(RowGameGUI gameView) {
        this.gameView = gameView;
    }
}
