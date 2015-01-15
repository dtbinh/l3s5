package oie;

/**
 * A player in the "jeu de l'oie" game
 */
public class Player {

    /**
	 * current cell of the player
	 * @uml.property  name="cell"
	 * @uml.associationEnd  
	 */
    protected Cell cell;
    
    /** name of the player*/
    protected String name;
    
    /** 
     * @param name the name of this player
     * @param cell the starting cell of this player
     */
    public Player (String name, Cell cell){
        this.name = name;
        this.cell = cell;
    }
    
	/** 
	 * A player with no current cell (== null)
	 * @param name the name of this player
     */
    public Player (String name){
        this(name, new ZeroCell());
    }
    
    @Override
    public String toString() {
		return name;
	}
	
    /**
	 * returns the current cell of the player 
	 * @return  the current cell of the player
	 * @uml.property  name="cell"
	 */
    public Cell getCell() {
    	//System.out.println(this.name+" is in cell "+this.cell+", ");
    	return this.cell;
    }
    
    /**
	 * changes the cell of the player 
	 * @param newCell  the new cell
	 * @uml.property  name="cell"
	 */
    public void setCell(Cell newCell) { 
       this.cell = newCell;
    }  
    
    /**
     * result of a 1d6 throw
     * @return random result of a 1d6 throw 
     */
    private int oneDiceThrow() {	
       return ((int) (Math.random()*10000) % 6)+ 1; 
    }
    
    /**
     * result of a 2d6 throw 
     * @return random result of a 2d6 throw
     */ 	
    public int twoDicesThrow() {
        int result = oneDiceThrow() + oneDiceThrow();
        System.out.print(this.name+" throws "+result+", ");	
        return result;
    }

}
