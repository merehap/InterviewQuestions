import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class CrackingTheCodingInterview {
	
	public static boolean onlyUniqueCharacters(String text) {
		Set<Character> characters = new HashSet<>();
		for(char c : text.toCharArray()) {
			characters.add(c);
		}
		
		return text.length() == characters.size();
	}
	
	public static void urlify(char[] input) {
		
		if(input.length <= 0) {
			return;
		}
		
		int index = input.length - 1;
		while(input[index] == ' ' && index > 0) {
			index--;
		}
		
		if(index <= 0) {
			return;
		}
		
		int stringEnd = input.length - 1;
		while(index > 0) {
			int endIndex = index;
			while(input[index] != ' ') {
				index--;
				
				if(index < 0) {
					return;
				}
			}
			
			for(int replaceIndex = endIndex; replaceIndex > index; replaceIndex--) {
				input[stringEnd] = input[replaceIndex];
				stringEnd--;
			}
			
			input[index] = '%';
			input[index + 1] = '2';
			input[index + 2] = '0';
		}
		
		
		
	}
	
	public static boolean palindromePermutation(String input) {
		Map<Character, Integer> counts = new HashMap<>();
		
		for(char c : input.toCharArray()) {
			if(!counts.containsKey(c)) {
				counts.put(c, 1);
			} else {
				counts.put(c, counts.get(c) + 1);
			}
		}
		
		boolean foundOddMember = false;
		for(Entry<Character, Integer> entry : counts.entrySet()) {
			if(entry.getValue() % 2 == 1) {
				if(foundOddMember) {
					return false;
				} else {
					foundOddMember = true;
				}
			}
		}
		
		return true;
	}
	
	public static boolean oneAway(String s0, String s1) {
		if(s0 == null || s1 == null) {
			return false;
		}
		
		switch(s0.length() - s1.length()) {
		case -1:
			return hasOneRemoved(s1, s0);
		case  0:
			int difference = 0;
			for(int index = 0; index < s0.length(); index++) {
				if(s0.charAt(index) != s1.charAt(index)) {
					difference++;
				}
			}
			
			return difference == 1;
		case  1:
			return hasOneRemoved(s0, s1);
		default: return false;
		}
	}
	
	private static boolean hasOneRemoved(String longer, String shorter) {
		
		int shorterOffset = 0;
		for(int index = 0; index < longer.length(); index++) {
			if(longer.charAt(index) != longer.charAt(index - shorterOffset)) {
				if(shorterOffset == 0) {
					shorterOffset = 1;
					index--;
				} else {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static class Graph {
		private final Node[] nodes;
		
		public Graph(final Node[] nodes) {
			this.nodes = nodes;
		}
	}
	
	public static class Node {
		private final String name;
		private final Node[] neighbors;
		private boolean visited;
		
		public Node(final String name, final Node[] neighbors) {
			this.name = name;
			this.neighbors = neighbors;
			this.visited = false;
		}
		
		public void depthFirstSearch() {
			this.visited = true;
			for(Node neighbor : neighbors) {
				if(!neighbor.visited) {
					neighbor.depthFirstSearch();
				}
			}
		}
		
		public void breadthFirstSearch() {
			this.visited = true;
			
			Queue<Node> queue = new LinkedList<>();
			queue.add(this);
			
			while(!queue.isEmpty()) {
				
				Node current = queue.poll();
				
				for(Node neighbor : current.neighbors) {
					if(!neighbor.visited) {
						neighbor.visited = true;
						queue.add(neighbor);
					}
				}
			}
		}
	}
	
	public static List<Node> routeBetweenNodes(Node node0, Node node1) {
		
		
		return null;
	}
	
	public static <T> T getKthFromLast(LinkedList<T> input, int k) {
		Stack<T> stack = new Stack<T>();
		while(input.peek() != null) {
			stack.push(input.removeFirst());
		}
		
		for(int index = 0; index < k; index++) {
			stack.pop();
		}
		
		return stack.peek();
	}

	public static String stringCompression(String input) {
		StringBuilder builder = new StringBuilder();
		
		for(int index = 0; index < input.length(); index++) {
			char current = input.charAt(index);
			int offset = 1;
			while(index + offset < input.length()
					&& input.charAt(index + offset) == current) {
				offset++;
			}
			
			builder.append(current);
			builder.append(offset);
		}
		
		return builder.length() < input.length()
				? builder.toString()
				: input;
	}
	
	public static void rotateImage(int[][] image) {
		
		for(int i = 0; i <= image.length / 2; i++) {
			for(int j = 0; j <= image.length / 2; j++) {
				int temp = image[i][j];
				image[i][j] = image[j][image.length - i - 1];
				image[j][image.length - i - 1] =
						image[image.length - i - 1][image.length - j - 1];
				image[image.length - i - 1][image.length - j - 1] =
						image[image.length - j - 1][i];
				image[image.length - j - 1][i] = temp;
			}
		}
	}
	
	public static void zeroMatrix(int[][] matrix) {
		Set<Integer> zeroedRows = new HashSet<>();
		Set<Integer> zeroedColumns = new HashSet<>();
		
		for(int row = 0; row < matrix.length; row++) {
			if(zeroedRows.contains(row)) {
				continue;
			}
			
			for(int col = 0; col < matrix[0].length; col++) {
				if(zeroedColumns.contains(col)) {
					continue;
				}
				
				if(matrix[col][row] == 0) {
					zeroedColumns.add(col);
					zeroedRows.add(row);
					
					for(int zeroRow = 0; zeroRow < matrix.length; zeroRow++) {
						matrix[col][zeroRow] = 0;
					}
					
					for(int zeroCol = 0; zeroCol < matrix.length; zeroCol++) {
						matrix[zeroCol][row] = 0;
					}
				}
			}
		}
	}
	
	public static class MinStack {
		
		private final Stack<Integer> stack;
		private final LinkedList<Integer> minList;
		
		public MinStack() {
			this.stack = new Stack<>();
			this.minList = new LinkedList<>();
		}
		
		public void push(int value) {
			this.stack.add(value);
			if(this.minList.isEmpty() || value <= this.minList.peek()) {
				this.minList.addFirst(value);
			}
		}
		
		public int pop() {
			if(this.stack.isEmpty()) {
				throw new RuntimeException("Stack is empty.");
			}
			
			if(this.stack.peek() == this.minList.getFirst()) {
				this.minList.removeFirst();
			}
			
			return this.stack.pop();
		}
		
		public int min() {
			return this.minList.getFirst();
		}
	}
}
