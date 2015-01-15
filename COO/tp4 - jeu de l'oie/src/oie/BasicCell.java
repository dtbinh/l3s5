package oie;

/**
 * Abstract class representing a cell
 */
public abstract class BasicCell implements Cell {

	// the index of cell
	/**
	 * @uml.property  name="index"
	 */
	protected int index;
	
	// the player in the cell
	/**
	 * @uml.property  name="playerInTheCell"
	 * @uml.associationEnd  
	 */
	protected Player playerInTheCell;
	
	/**
	 * constructor for the instances of the class
	 * @param i the index of the cell
	 */
	public BasicCell(int i) {
		this.index = i;
		this.playerInTheCell = null;
	}
	
	/**
	 * @return <tt>true</tt> if and only if the player in this cell can freely
	 *  leaves the cell, else he must wait for another player to reach this cell 
	 *  or wait a given number of turns
	 */
	public abstract boolean canBeLeft();
	
	/**
	 * returns the index of the cell
	 * @return  the index of the cell
	 * @uml.property  name="index"
	 */
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * returns the index of the cell really reached by a player when he reaches 
	 * this cell
	 * @param diceThrow the result of the dices when the player reaches this cell
	 * @return the index of the cell effectively reached when a player reaches
	 *          this cell after the given throw of dices
	 */
	public abstract int consequence(int diceThrow);
	
	/**
	 * returns <code> true </code> if there is a player in the cell
	 */
	public boolean isBusy() {
		return (this.playerInTheCell != null);
	}
	
	/**
	 * sets a player in the cell
	 * @param player the player to put in the cell
	 */
	public void setPlayer(Player player) {
		if (this.isBusy()) {
			Player oldPlayer = this.playerInTheCell;
			BasicCell oldCell = (BasicCell) player.getCell();
			oldCell.playerInTheCell = oldPlayer;
			oldPlayer.setCell(oldCell);
		}
		this.playerInTheCell = player;
		player.setCell(this);
	}
	
	/**
	 * returns the player in the cell
	 * @return the player in the cell
	 */
	public Player getPlayer() {
		return this.playerInTheCell;
	}
	
	@Override
	public String toString() {
		return ""+this.index+"";
	}

}
