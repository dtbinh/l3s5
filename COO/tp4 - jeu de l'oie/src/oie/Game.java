package oie;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing the "jeu de l'oie" game
 */
public class Game {
	
	// the list of players
	public static List<Player> thePlayers;
	
	// the board
	/**
	 * @uml.property  name="board"
	 * @uml.associationEnd  
	 */
	private Board board;
	
	// boolean that indicates if the game is terminated or not
	private boolean over;
	
	/**
	 * constructor for the instances of the class
	 * @param b the board
	 */
	public Game(Board b) {
		this.board = b;
		Game.thePlayers = new LinkedList<Player>();
		this.over = false;
	}
	
	/**
	 * add a player to the game
	 * @param p the player to add to the game
	 */
	public void addPlayer(Player p) {
		Game.thePlayers.add(p);
		p.setCell(this.board.getCell(0));
	}
	
	/**
	 * returns the board
	 * @return  the board
	 * @uml.property  name="board"
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * returns <code> true </code> if the game 
	 * @return the boolean that indicates if the game is terminated or not
	 */
	public boolean gameOver() {
		return this.over;
	}
	
	/**
	 * <code> p </code> plays a turn
	 * @param p the player who plays a turn
	 */
	public void playATurn(Player p) {
		System.out.print("\n"+p+" is in cell "+p.getCell()+", ");
		if (p.getCell().canBeLeft()) {
			int score = p.twoDicesThrow();
			if (score <= (Board.nbOfCells - 1 - p.getCell().getIndex())) {
				this.board.getCell(this.board.getCell(p.getCell().getIndex() + score).consequence(score)).setPlayer(p);
			}
			else {
				int advancedScore = Board.nbOfCells - 1 - p.getCell().getIndex();
				int steppedBackScore = score - advancedScore;
				this.board.getCell(this.board.getCell(p.getCell().getIndex() + advancedScore - steppedBackScore).consequence(advancedScore - steppedBackScore)).setPlayer(p);
			}
		}
		else {
			System.out.print(p+" cannot play");
		}
		if (p.getCell().getIndex() == (Board.nbOfCells - 1)) {
			this.over = true;
			System.out.println(", "+p+" has won");
		}
	}
	
	/**
	 * make the players throw dices until one of them wins (arrives at the last cell)
	 */
	public void play() {
		while (!this.gameOver()) {
			Iterator<Player> it = Game.thePlayers.iterator();
			while (it.hasNext() && this.over == false) {
				Player p = it.next();
				this.playATurn(p);
			}
		}
	}
	
	/**
	 * main method of the class
	 * @param args the list of the arguments
	 */
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage : java -jar mygoosegame.jar p1 p2 ... pn");
			System.out.println("Exemples :");
			System.out.println("java -jar mygoosegame.jar A pour lancer un jeu de l'oie avec un joueur nommé A");
			System.out.println(	"java -jar mygoosegame.jar A B pour lancer un jeu de l'oie avec deux joueurs nommés A et B");
			System.out.println(	"java -jar mygoosegame.jar A B C pour lancer un jeu de l'oie avec trois joueurs nommés A, B et C");
			System.out.println(	"etc...");
		}
		else {
			Game g = new Game(new GooseBoard());
			for (int i = 0 ; i < args.length ; i++) {
				Player p = new Player(args[i]);
				g.addPlayer(p);
			}
			g.play();
		}
	}

}
