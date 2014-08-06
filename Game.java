
public class Game
{
	private int[][] _mineGrid;
	private int[][] _deckGrid;
	private int _width;
	private int _height;

   public static void main(String[] args) {
      System.out.println("=== Demineur ===");
      Game game = new Game();
      game.newGrid(8, 8, 10);
      game.printMineGrid();
      game.printDeckGrid();
   }

	public void newGrid(int width, int height, int mines) {
		this._height = height;
		this._width  = width;

		int i = 0;
		int rand_x = 0;
		int rand_y = 0;
		this._mineGrid = new int[this._width][this._height];
		this._deckGrid = new int[this._width][this._height];

		while(i < mines) {
			rand_x = (int) Math.floor(Math.random() * this._width);
			rand_y = (int) Math.floor(Math.random() * this._height);
			if(this._mineGrid[rand_x][rand_y] != 1) {
				this._mineGrid[rand_x][rand_y] = 1;
				i++;
			}
		}
	}

	public void printMineGrid() {
		for (int i = 0; i < this._mineGrid.length ; i++) {
			for (int j = 0; j < this._mineGrid.length ; j++) {
				if(this._mineGrid[i][j] == 1) {
					System.out.print("X");
				} else {
					System.out.print(countMine(i, j));
				}
			}
			System.out.println();
		}
	}

	public void printDeckGrid() {
		for (int i = 0; i < this._deckGrid.length ; i++) {
			for (int j = 0; j < this._deckGrid.length ; j++) {
				System.out.print(this._deckGrid[i][j]);
			}
			System.out.println();
		}
	}

	private int countMine(int x, int y) {
		int nb = 0;

		if(x - 1 >= 0) {
			if(y - 1 >= 0) if(this._mineGrid[x - 1][y - 1] == 1) 			nb++;
			if(this._mineGrid[x - 1][y] == 1) 								nb++;
			if(y + 1 < this._height) if(this._mineGrid[x - 1][y + 1] == 1) 	nb++;
		}
		if(x + 1 < this._width) {
			if(y - 1 >= 0) if(this._mineGrid[x + 1][y - 1] == 1) 			nb++;
			if(this._mineGrid[x + 1][y] == 1) 								nb++;
			if(y + 1 < this._height) if(this._mineGrid[x + 1][y + 1] == 1) 	nb++;
		}
		if(y - 1 >= 0) if(this._mineGrid[x][y - 1] == 1) 					nb++;
		if(y + 1 < this._height) if(this._mineGrid[x][y + 1] == 1) 			nb++;

		return nb;
	}
}
