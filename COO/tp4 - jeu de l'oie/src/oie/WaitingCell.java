package oie;

/**
 * Class representing a waiting cell
 */
public class WaitingCell extends BasicCell {

	// the rounds the player in the cell has to stay blocked without playing
	/**
	 * @uml.property  name="roundsToWait"
	 */
	private int roundsToWait;
	
	// the rounds the player in the cell has waited
	private int roundsWaited;

	/**
	 * constructor for the instances of the class
	 * @param i the index of the cell
	 * @param rtw the rounds the player in the cell has to stay blocked without playing
	 */
	public WaitingCell(int i,int rtw) {
		super(i);
		this.roundsToWait = rtw;
		this.roundsWaited = 0;
	}
	
	@Override
	public boolean canBeLeft() {
		if (this.roundsWaited < this.roundsToWait) {
			this.roundsWaited++;
			return false;
		}
		else {
			return true;
		}		
	}
	
	@Override
	public void setPlayer(Player p) {
		super.setPlayer(p);
		this.roundsWaited = 0;
	}
	
	@Override
	public int consequence(int diceThrow) {
		System.out.print("reaches cell "+this.index);
		return this.index;
	}
	
	/**
	 * returns the rounds the player in the cell has to stay blocked without playing
	 * @return  the rounds the player in the cell has to stay blocked without playing
	 * @uml.property  name="roundsToWait"
	 */
	public int getRoundsToWait() {
		return this.roundsToWait;
	}

}
