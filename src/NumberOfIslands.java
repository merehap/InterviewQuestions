
public class NumberOfIslands {

	/**
	 * 11110
	 * 11010
	 * 11000
	 * 00000
	 */
	public static int numberOfIslands(char[][] terrain) {
		int count = 0;
		for(int i = 0; i < terrain.length; i++) {
			for(int j = 0; j < terrain[0].length; j++) {
				if(terrain[i][j] == '1') {
					count++;
					markIsland(terrain, i, j);
				}
			}
		}
		
		return count;
	}
	
	private static void markIsland(char[][] terrain, int x, int y) {
		if(x < 0 || y < 0 || x > terrain.length || y > terrain.length
				|| terrain[x][y] != '1') {
			return;
		}
		
		terrain[x][y] = 'X';
		
		markIsland(terrain, x - 1, y);
		markIsland(terrain, x + 1, y);
		markIsland(terrain, x, y - 1);
		markIsland(terrain, x, y + 1);
	}
}
