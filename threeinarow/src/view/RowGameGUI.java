package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import controller.RowGameController;
import model.RowGameModel;


public class RowGameGUI implements RowGameView
{
    private JFrame gui = new JFrame("Three in a Row");
    private RowGameBoardView gameBoardView;
    private JButton reset = new JButton("Reset");
    private RowGameStatusView gameStatusView;

    public JFrame getGui() {
        return gui;
    }

    public void setGui(JFrame gui) {
        this.gui = gui;
    }

    public RowGameBoardView getGameBoardView() {
        return gameBoardView;
    }

    public void setGameBoardView(RowGameBoardView gameBoardView) {
        this.gameBoardView = gameBoardView;
    }

    public JButton getReset() {
        return reset;
    }

    public void setReset(JButton reset) {
        this.reset = reset;
    }

    public RowGameStatusView getGameStatusView() {
        return gameStatusView;
    }

    public void setGameStatusView(RowGameStatusView gameStatusView) {
        this.gameStatusView = gameStatusView;
    }

    private RowGameController gameController;


    /**
     * Creates a new game initializing the GUI.
     *
     * @param gameController
     */
    public RowGameGUI(controller.RowGameController gameController) {
	    this.gameController = gameController;
	
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

	    gameBoardView = new RowGameBoardView(this.gameController);
        JPanel gamePanel = gameBoardView.getGamePanel();

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);

	    gameStatusView = new RowGameStatusView(this.gameController);
        JPanel messages = gameStatusView.getMessages();

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.reset(gameController.getModel());
            }
        });
    }

    /**
     * Updates the game view after the game model
     * changes state.
     *
     * @param gameModel The current game model
     */
    public void update(RowGameModel gameModel) {
	gameBoardView.update(gameModel);

	gameStatusView.update(gameModel);
    }
}
