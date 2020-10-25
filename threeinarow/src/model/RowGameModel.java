package model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The RowGameModel class represents the grid in the game.
 */
public class RowGameModel
{
	// Fixed the Violation reported in Homework 1:
	// 'Encapsulation violated in ThreeInARowGame class'
	// Fix: Changed data attributes from public accessor to private accessor.
	// Additionally, added getter and setter for blocksData to be used by other classes.
    private static final String GAME_END_NOWINNER = "Game ends in a draw";

    private int rows = 3;
	private int columns = 3;
    private RowBlockModel[][] blocksData = new RowBlockModel[rows][columns];
	private PropertyChangeSupport pcs;

    /**
     * The current player taking their turn
     */
    private String player = "1";
    private int movesLeft = 9;

    private String finalResult = null;


    public RowGameModel() {
		super();
	
		for (int row = 0; row < this.rows; row++) {
		    for (int col = 0; col < this.columns; col++) {
			blocksData[row][col] = new RowBlockModel(this);
		    } // end for col
		} // end for row
		pcs = new PropertyChangeSupport(this);
    }

    /*
    This constructor is for making the grid extensible.
    Here, we assume that the user provides a square grid.
     */
	public RowGameModel(int rows, int columns) {
		super();
		if(rows != columns) {
			throw new IllegalArgumentException("Number of rows and columns should be equal");
		}
		if(rows < 3 || columns < 3){
			throw new IllegalArgumentException("Number of rows and columns should be atleast 3");
		}

		this.rows = rows;
		this.columns = columns;
		this.blocksData = new RowBlockModel[rows][columns];
		this.movesLeft = rows * columns;
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.columns; col++) {
				blocksData[row][col] = new RowBlockModel(this);
			} // end for col
		} // end for row
		pcs = new PropertyChangeSupport(this);
	}

    public String getFinalResult() {
		return this.finalResult;
    }

    public void setFinalResult(String finalResult) {
		String oldFinalResult = getFinalResult();
		this.finalResult = finalResult;
		pcs.firePropertyChange("FinalResult", oldFinalResult, this.finalResult);
    }
    
    public RowBlockModel[][] getBlocksData() {
    	return this.blocksData;
    }
    
    public void setBlocksData(RowBlockModel[][] blocksData) {
		RowBlockModel[][] oldBlocksData = getBlocksData();
    	this.blocksData = blocksData;
		pcs.firePropertyChange("BlocksData", oldBlocksData, this.blocksData);
    }
    
    public String getPlayer() {
		return this.player;
	}

	public void setPlayer(String player) {
		String oldPlayer = getPlayer();
		this.player = player;
		pcs.firePropertyChange("Player", oldPlayer, this.player);
	}
	
	public int getMovesLeft() {
		return this.movesLeft;
	}

	public void setMovesLeft(int movesLeft) {
		int oldMovesLeft = getMovesLeft();
		this.movesLeft = movesLeft;
		pcs.firePropertyChange("MovesLeft", oldMovesLeft, this.movesLeft);

	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
	public void removePropertyChangeListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener(l);
	}
}
