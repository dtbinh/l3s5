package oie;

/** Class representing a goose cell */
public class GooseCell extends BasicCell {

	/**
	 * constructor for the instances of the class
	 * @param i the index of the cell
	 */
	public GooseCell(int i) {
		super(i);
	}
	
	/**
	 * @return <tt>true</tt> if and only if the player in this cell can freely
	 *  leaves the cell, else he must wait for another player to reach this cell 
	 *  or wait a given number of turns
	 */
	@Override
	public boolean canBeLeft() {
		return true;
	}
	
	/**
	 * returns the index of the cell really reached by a player when he reaches 
	 * this cell
	 * @param diceThrow the result of the dices when the player reaches this cell
	 * @return the index of the cell effectively reached when a player reaches
	 *          this cell after the given throw of dices
	 */
	@Override
	public int consequence(int diceThrow) {
		if ((this.index + diceThrow) <= (Board.nbOfCells - 1)) {
			System.out.print("reaches goose cell "+this.index+" and jumps to "+(this.index + diceThrow));
			return (this.index + diceThrow);
		}
		else {
			int advancedScore = Board.nbOfCells - 1 - this.index;
			int steppedBackScore = diceThrow - advancedScore;
			System.out.print("reaches goose cell "+this.index+" and jumps to "+(Board.nbOfCells - 1 - steppedBackScore));
			return (Board.nbOfCells - 1 - steppedBackScore);
		}
	}

}
