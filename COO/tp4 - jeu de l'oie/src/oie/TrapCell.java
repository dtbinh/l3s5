package oie;

/** Class representing a trap cell */
public class TrapCell extends BasicCell {

	/**
	 * constructor for the instances of the class
	 * @param i the index of the cell
	 */
	public TrapCell(int i) {
		super(i);
	}
	
	/**
	 * @return <tt>true</tt> if and only if the player in this cell can freely
	 *  leaves the cell, else he must wait for another player to reach this cell 
	 *  or wait a given number of turns
	 */
	public boolean canBeLeft() {
		return false;
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
		System.out.print("reaches cell "+this.index);
		return this.index;
	}

}
