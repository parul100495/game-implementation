package controller;

import model.RowGameModel;

/**
 * This class is used to pick the type of game - either TicTacToe or Threeinarow
 */
public class PickStrategy {

    private RowGameRulesStrategy strategy;

    public PickStrategy(RowGameRulesStrategy strategy) {
        this.strategy = strategy;
    }

    public void startUp() {
        this.strategy.startUp();
    }

    public void resetStrategy(RowGameModel gameModel) {
        this.strategy.reset(gameModel);
    }
}
