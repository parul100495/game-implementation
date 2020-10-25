package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.RowGameModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class RowGameStatusView implements RowGameView {
    private JTextArea playerturn = new JTextArea();
    private JPanel messages = new JPanel(new FlowLayout());

    public JTextArea getPlayerturn() {
        return playerturn;
    }

    public void setPlayerturn(JTextArea playerturn) {
        this.playerturn = playerturn;
    }

    public JPanel getMessages() {
        return messages;
    }

    public void setMessages(JPanel messages) {
        this.messages = messages;
    }

    public RowGameStatusView(controller.RowGameController gameController) {
        super();

        messages.setBackground(Color.white);
        messages.add(playerturn);
        gameController.getModel().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                update(gameController.getModel());
            }
        });
    }

    /**
     * Updates the status of the game in the UI
     *
     * @param gameModel
     */
    public void update(RowGameModel gameModel) {
        if (gameModel.getFinalResult() == null) {
            if (gameModel.getPlayer().equals("1")) {
                playerturn.setText("Player 1 to play 'X'");
            } else {
                playerturn.setText("Player 2 to play 'O'");
            }
        } else {
            playerturn.setText(gameModel.getFinalResult());
        }
    }
}
