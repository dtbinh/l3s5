package oie;

/**
 * Class representing a teleportation cell
 */
public class TeleportationCell extends BasicCell {
	
	// the cell where the player will be teleported
	/**
	 * @uml.property  name="newCell"
	 * @uml.associationEnd  
	 */
	private BasicCell newCell;

	/**
	 * constructor for the instances of the class
	 * @param i the index of the cell
	 * @param nc the cell where the player will be teleported
	 */
	public TeleportationCell(int i,BasicCell nc) {
		super(i);
		this.newCell = nc;
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
		System.out.print("reaches cell "+this.index+" and jumps to "+(this.newCell.getIndex()));
		return this.newCell.getIndex();
	}

}
	