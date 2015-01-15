package oie;

/** Class representing a goose board */
public class GooseBoard extends Board {

	/**
	 * constructor for the instances of the class
	 */
	public GooseBoard() {
		super(64);
	}
	
	// initialize the zero cell
	private void initZeroCell() {
		this.theCells[0] = new ZeroCell();
	}
	
	// initialize the goose cells
	private void initGooseCells() {
		this.theCells[9] = new GooseCell(9);
		this.theCells[18] = new GooseCell(18);
		this.theCells[27] = new GooseCell(27);
		this.theCells[36] = new GooseCell(36);
		this.theCells[45] = new GooseCell(45);
		this.theCells[54] = new GooseCell(54);
	}
	
	// initialize the trap cells
	private void initTrapCells() {
		this.theCells[31] = new TrapCell(31);
		this.theCells[52] = new TrapCell(52);
	}
	
	// initialize the waiting cell
	private void initWaitingCell() {
		this.theCells[19] = new WaitingCell(19,2);
	}
	
	// initialize the teleportation cells
	private void initTeleportationCells() {
		this.theCells[6] = new TeleportationCell(6,new NormalCell(12));
		this.theCells[42] = new TeleportationCell(42,new NormalCell(30));
		this.theCells[58] = new TeleportationCell(58,new NormalCell(1));
	}
	
	// initialize the normal cells
	private void initNormalCells() {
		for (int i = 1 ; i < this.getNbOfCells() ; i++) {
			if (this.theCells[i] == null)
				theCells[i] = new NormalCell(i);
		}
	}

	@Override
	public void initBoard() {
		this.initZeroCell();
		this.initGooseCells();
		this.initTrapCells();
		this.initWaitingCell();
		this.initTeleportationCells();
		this.initNormalCells();
	}
	
}
