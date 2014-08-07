import Game;

public class Demineur {

		System.out.println("=== Demineur ===");
		Game game = new Game();
		game.newGrid(8, 8, 10);
		game.printMineGrid();
		game.printDeckGrid();
}
