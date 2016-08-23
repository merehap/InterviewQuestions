import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DynamicProgramming {

//	A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
//	steps at a time. Implement a method to count how many possible ways the child can run up the
//	stairs.
	public static int tripleStep(int totalSteps) {
		return tripleStep(totalSteps, new Integer[totalSteps+1]);
	}
	
	private static int tripleStep(int totalSteps, Integer[] cache) {
		
		if(totalSteps < 0) {
			return 0;
		}
		
		if(totalSteps == 0) {
			return 1;
		}
		
		if(cache[totalSteps] != null) {
			return cache[totalSteps];
		}
		
		int result = tripleStep(totalSteps - 3)
				+ tripleStep(totalSteps - 2)
				+ tripleStep(totalSteps - 1);
		
		cache[totalSteps] = result;
		
		return result;
	}
	
	// Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
	//	The robot can only move in two directions, right and down, but certain cells are "off limits" such that
	//	the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
	//	the bottom right.
	public static List<Direction> robotInAGrid(
			Status[][] grid,
			List<Direction> previousPath,
			Map<Position, Boolean> cache,
			Position position) {
		
		if(grid[position.col][position.row] == Status.BLOCKED || grid.length == 0 || grid[0].length == 0) {
			return null;
		}
		
		if(cache.get(position) == false) {
			return null;
		}
		
		if(position.col < grid.length - 1) {
			List<Direction> currentPath = new ArrayList<>(previousPath);
			currentPath.add(Direction.RIGHT);
			List<Direction> fullPath = robotInAGrid(
					grid, currentPath, cache, new Position(position.col + 1, position.row));
			if(fullPath != null) {
				return fullPath;
			}
			
			cache.put(position, false);
		}
		
		if(position.col < grid[0].length - 1) {
			List<Direction> currentPath = new ArrayList<>(previousPath);
			currentPath.add(Direction.DOWN);
			List<Direction> fullPath = robotInAGrid(
					grid, currentPath, cache, new Position(position.col, position.row + 1));
			if(fullPath != null) {
				return fullPath;
			}
			
			cache.put(position, false);
		}
		
		return null;
	}
	
	public static enum Status {
		OPEN,
		BLOCKED
	}
	
	public static enum Direction {
		RIGHT,
		DOWN
	}
	
	public static class Position {
		public int col;
		public int row;
		
		public Position(int col, int row) {
			this.col = col;
			this.row = row;
		}
	}
	
	public static List<String> permutations(
			String fullPrefix, List<Character> suffix) {
		
		List<String> result = new ArrayList<>();
		if(suffix.size() == 0) {
			result.add(fullPrefix);
			return result;
		}
		
		for(int index = 0; index < suffix.size(); index++) {
			List<Character> current = new ArrayList<>(suffix);
			char prefix = current.remove(index);
			result.addAll(permutations(fullPrefix + prefix, current));
		}
		
		return result;
	}
	
}
