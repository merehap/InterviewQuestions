import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class WordLadder {

	/*
	 * start = "hit"
	   end = "cog"
	   dict = ["hot","dot","dog","lot","log"]
	 */
	
	public static int wordLadder(
			final String start,
			final String end,
			final List<String> dict) {
		
		if(start.length() != end.length()) {
			throw new IllegalArgumentException();
		}
		
		final Tree tree = createTree(start, new HashSet<>(dict));
		return tree.shortestDistance(end);
	}
	
	public static Tree createTree(
			final String root,
			final Set<String> words) {
		
		words.remove(root);
		
		List<Tree> children = new ArrayList<>();
		for(final String child : words) {
			if(isOneOff(root, child)) {
				children.add(createTree(child, new HashSet<>(words)));
			}
		}
		
		return new Tree(root, children);
	}
	
	private static boolean isOneOff(
			final String s1,
			final String s2) {
		
		int diffCount = 0;
		for(int index = 0; index < s1.length(); index++) {
			if(s1.charAt(index) != s2.charAt(index)) {
				diffCount++;
			}
		}
		
		return diffCount == 1;
	}
	
	public static class Tree {
		
		private final String value;
		private final List<Tree> subtrees; 
		
		public Tree(final String value, List<Tree> subtrees) {
			this.value = value;
			this.subtrees = new ArrayList<Tree>(subtrees);
		}
		
		public int shortestDistance(final String target) {
			
			if(target.equals(this.value)) {
				return 0;
			}
			
			int min = Integer.MAX_VALUE; 
			for (final Tree child : subtrees) {
				min = Math.min(min, child.shortestDistance(target));
			}
			
			return min;
		}
	}
}
