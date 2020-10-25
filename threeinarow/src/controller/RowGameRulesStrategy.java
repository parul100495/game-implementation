package controller;

import model.RowGameModel;

/**
 * This interface is implemented by Controller for threeinarow and tictactoe.
 */
public interface RowGameRulesStrategy
{
    public void reset(RowGameModel gameModel);

    public void move(RowGameModel gameModel, int row, int col);

    public boolean isWin(RowGameModel gameModel, int row, int col);

    public boolean isTie(RowGameModel gameModel);

    public void startUp();
}
