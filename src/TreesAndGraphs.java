import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class TreesAndGraphs {

	public static boolean findPath(GraphNode source, GraphNode target) {
		
		source.visited = true;
		
		if(target.visited) {
			return true;
		}
		
		for(GraphNode node : source.neighbors) {
			if(!node.visited) {
				if(findPath(node, target)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean findPathBfs(GraphNode source, GraphNode target) {
		
		Queue<GraphNode> queue = new LinkedList<>();
		
		source.visited = true;
		queue.add(source);
		
		while(!queue.isEmpty()) {
			GraphNode current = queue.poll();
			
			
			if(target.visited) {
				return true;
			}
			
			for(GraphNode neighbor : current.neighbors) {
				if(!neighbor.visited) {
					neighbor.visited = true;
					queue.add(neighbor);
				}
			}
		}
		
		return false;
	}
	
	public static class GraphNode {
		
		private boolean visited;
		private List<GraphNode> neighbors;
	}
}
