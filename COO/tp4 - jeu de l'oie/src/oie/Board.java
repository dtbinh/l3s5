package oie;

/**
 * Abstract class representing a board
 */
public abstract class Board {

	// the number of cells composing the board
	/**
	 * @uml.property  name="nbOfCells"
	 */
	protected static int nbOfCells;
	
	// the cells composing the board
	/**
	 * @uml.property  name="theCells"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	protected Cell[] theCells;
	
	/**
	 * constructor for the instances of the class
	 * @param noc the number of cells composing the board
	 */
	public Board(int noc) {
		Board.nbOfCells = noc;
		this.theCells = new Cell[Board.nbOfCells + 1];
		this.initBoard();
	}
	
	/**
	 * initialize the board by creating the cells
	 */
	public abstract void initBoard();
	
	/**
	 * returns the cell number <code> number </code>
	 * @param number the number of the cell we want to return
	 * @return the cell number <code> number </code>
	 * @exception ArrayIndexOutOfBoundsException if <code> number </code> exceeds the length of the board
	 */
	public Cell getCell(int number) throws ArrayIndexOutOfBoundsException {
		return this.theCells[number];
	}
	
	/**
	 * returns the length of the board (the number of cells)
	 * @return  the length of the board
	 * @uml.property  name="nbOfCells"
	 */
	public int getNbOfCells() {
		return Board.nbOfCells;
	}
	
}
