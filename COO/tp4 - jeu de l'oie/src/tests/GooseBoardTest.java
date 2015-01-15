package tests;

import static org.junit.Assert.*;

import oie.Board;
import oie.GooseBoard;

import org.junit.Test;

public class GooseBoardTest {

	@Test
	public void testGetNbOfCells() {
		Board b = new GooseBoard();
		assertEquals("le nombre de cases est-il correct ?",b.getNbOfCells(),64);
	}

}
