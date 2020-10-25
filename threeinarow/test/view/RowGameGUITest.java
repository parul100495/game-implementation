package view;

import controller.RowGameController;
import controller.RowGameControllerThreeInARow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for GUI View.
 */
public class RowGameGUITest {
    private RowGameController gameController;

    @Before
    public void initiate() {
        int rows = 3;
        int columns = 3;
        gameController = new RowGameControllerThreeInARow(rows, columns);
    }

    @After
    public void finish() {
        gameController = null;
    }

    @Test
    public void testUpdateBlock() {
        gameController.move(this.gameController.getGameModel(), 2, 0);
        assertEquals(gameController.getView().getGameBoardView().getBlocks()[2][0].getText(), "X");
        assertFalse(gameController.getView().getGameBoardView().getBlocks()[2][0].isEnabled());
    }
}
