package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import model.*;

/**
 * Already provided test class for model.
 */
public class RowGameModelTest {
    private model.RowGameModel gameModel;

    @Before
    public void setUp() {
        gameModel = new model.RowGameModel();
    }

    @After
    public void tearDown() {
        gameModel = null;
    }

    @Test
    public void testNewGame() {
        assertEquals("1", gameModel.getPlayer());
        assertEquals(9, gameModel.getMovesLeft());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
        model.RowBlockModel block = new model.RowBlockModel(null);
    }
}
