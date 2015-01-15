package tests;

import static org.junit.Assert.*;

import oie.Cell;
import oie.Player;

import org.junit.Before;
import org.junit.Test;

/**
 * @author  salla
 */
public class PlayerTest {

	/**
	 * @uml.property  name="cell"
	 * @uml.associationEnd  
	 */
	private Cell cell;
	/**
	 * @uml.property  name="player"
	 * @uml.associationEnd  
	 */
	private Player player;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		this.cell = new DummyCell();
		this.player = new Player("playerTest");
	}

	@Test
	public void testPlayer() {
		assertNotNull("player created ?", player);
	}

	@Test
	public void testGetSetCell() {
		// same tests for setter and getter 
		//assertNull("no cell set", player.getCell());
		player.setCell(cell);
		assertNotNull("cell set", player.getCell());		
		assertSame("same cell ?",cell, player.getCell());
		assertNotSame("not same cell ?",new DummyCell(), player.getCell());
	}

	/** A useless (mock) class of interface cell only to achieve tests */
	private class DummyCell implements Cell {
		public boolean canBeLeft() {
			return false;
		}
		public int getIndex() {
			return 0;
		}
		public int consequence(int diceThrow) {
			return 0;
		}
		public boolean isBusy() {
			return false;
		}
		public void setPlayer(Player player) {
		}
		public Player getPlayer() {
			return null;
		}
	}
}

