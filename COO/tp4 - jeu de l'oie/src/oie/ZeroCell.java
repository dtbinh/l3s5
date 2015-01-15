package oie;

import java.util.List;

/** Class representing the cell at the index 0 */
public class ZeroCell extends BasicCell {
   
	// the list of the players at the start of the game
    private List<Player> players;

    /**
     * constructor for the instances of the class
     */
    public ZeroCell(){
		super(0);
    	this.players = Game.thePlayers;
    }
    
    @Override
    public boolean canBeLeft(){
    	return true;
    }

    @Override
    public int getIndex(){
    	return 0;
    }

    @Override
    public Player getPlayer(){
	if(this.players.isEmpty())
	    return null;
	else
		return this.players.get(0);
    }

    @Override
    public void setPlayer(Player p){
    	this.players.add(p);
    }

	@Override
	public int consequence(int diceThrow) {
		return 0;
	}

	@Override
	public boolean isBusy() {
		return false;
	}
	
	@Override
	public String toString() {
		return "0";
	}

}
