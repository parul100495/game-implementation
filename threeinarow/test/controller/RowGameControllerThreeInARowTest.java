package controller;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for Threeinarow game
 */
public class RowGameControllerThreeInARowTest {
    private RowGameController gameController;
    private RowGameModel gameModel;

    @Before
    public void initiate() {
        int rows = 3;
        int columns = 3;
        gameController = new RowGameControllerThreeInARow(rows, columns);
        gameModel = new RowGameModel(rows, columns);
        RowBlockModel[][] blocksData = new RowBlockModel[rows][columns];

        /**
         * Initialise the grid as -
         * -------------------
         *   -     -     -
         * -------------------
         *   O     O     -
         * -------------------
         *   X     X     -
         * -------------------
         *
         */
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                blocksData[row][col] = new RowBlockModel(this.gameModel);
            }
        }
        blocksData[2][0].setContents("X");
        blocksData[2][1].setContents("X");
        blocksData[1][0].setContents("O");
        blocksData[1][1].setContents("O");

        blocksData[0][0].setIsLegalMove(true);
        blocksData[0][1].setIsLegalMove(true);
        blocksData[0][2].setIsLegalMove(false);

        blocksData[1][0].setIsLegalMove(false);
        blocksData[1][1].setIsLegalMove(false);
        blocksData[1][2].setIsLegalMove(false);

        blocksData[2][0].setIsLegalMove(false);
        blocksData[2][1].setIsLegalMove(false);
        blocksData[2][2].setIsLegalMove(true);

        gameController.getGameModel().setBlocksData(blocksData);
        gameController.getGameModel().setPlayer("1");
        gameController.getGameModel().setMovesLeft(5);
    }

    @After
    public void finish() {
        gameController = null;
    }

    @Test
    public void testIllegalMove() {
        gameController.move(this.gameController.getGameModel(), 1, 1);
        // the block content stays same as before, i.e. O
        assertEquals("O", gameController.getGameModel().getBlocksData()[1][1].getContents());
    }

    @Test
    public void testLegalMove() {
        gameController.move(this.gameController.getGameModel(), 0, 0);
        // the block content changes to the player 1's content as before, i.e. X
        assertEquals("X", gameController.getGameModel().getBlocksData()[0][0].getContents());
    }

    @Test
    public void testIsWin() {
        /**
         * -------------------
         *   -     -     -
         * -------------------
         *   O     O     -
         * -------------------
         *   X     X     X
         * -------------------
         */
        gameController.move(this.gameController.getGameModel(), 2, 2);
        // Player 1 should win
        assertTrue(this.gameController.isWin(this.gameController.getGameModel(), 2, 2));
        assertEquals("Player 1 wins!", this.gameController.getGameModel().getFinalResult());
    }

    @Test
    public void testIsTie() {
        gameController.move(this.gameController.getGameModel(), 0, 0); // Player 1
        gameController.move(this.gameController.getGameModel(), 2, 2); // Player 2
        gameController.move(this.gameController.getGameModel(), 1, 2); // Player 1
        gameController.move(this.gameController.getGameModel(), 0, 1); // Player 2
        gameController.move(this.gameController.getGameModel(), 0, 2); // Player 1
        /**
         * -------------------
         *   X     O     X
         * -------------------
         *   O     O     X
         * -------------------
         *   X     X     O
         * -------------------
         */
        assertEquals("Game ends in a draw", this.gameController.gameModel.getFinalResult());
        assertTrue(this.gameController.isTie(this.gameController.getGameModel()));
    }

    @Test
    public void testResetGame() {
        gameController.reset(this.gameController.getGameModel());
        for (int row = 0; row < gameController.getGameModel().getRows(); row++) {
            for (int col = 0; col < gameController.getGameModel().getColumns(); col++) {
                if (row == gameController.getGameModel().getRows() - 1) {
                    assertTrue(gameController.getGameModel().getBlocksData()[row][col].getIsLegalMove());
                } else {
                    assertFalse(gameController.getGameModel().getBlocksData()[row][col].getIsLegalMove());
                }
                assertEquals("", gameController.getGameModel().getBlocksData()[row][col].getContents());
            }
        }
    }
}
