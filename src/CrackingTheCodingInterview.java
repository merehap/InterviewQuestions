import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;


public class CrackingTheCodingInterview {
	
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
}
