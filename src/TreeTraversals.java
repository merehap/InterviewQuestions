import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class TreeTraversals {
	
	public static <T> List<T> preorder(Tree<T> tree) {
		List<T> result = new ArrayList<>();
		if(tree == null) {
			return result;
		}
		
		result.add(tree.value);
		result.addAll(preorder(tree.left));
		result.addAll(preorder(tree.right));
		
		return result;
	}
	
	public static <T> List<T> iterativePreorder(Tree<T> tree) {
		
		if(tree == null) {
			return new ArrayList<>();
		}
		
		List<T> result = new ArrayList<>();
		Stack<Tree<T>> stack = new Stack<>();
		
		Tree<T> node = tree;
		while(node != null) {
			
			if(stack.empty() || node != stack.peek()) {
				while(node != null) {
					result.add(node.value);
					stack.push(node);
					node = node.left;
				}
				
				node = stack.pop();
			}
			
			while(node.right == null) {
				if(stack.empty()) {
					break;
				}
				node = stack.pop();
			}
			
			node = node.right;
		}
		
		return result;
	}
	
	public static <T> List<T> iterativeInorderTraversal(Tree<T> tree) {
		List<T> result = new ArrayList<>();
		
		Stack<Tree<T>> stack = new Stack<>();
		
		Tree<T> root = tree;
		while(stack.isEmpty() || root != null) {
			
			if(root != null) {
				stack.push(root);
				root = root.left;
			} else {
				Tree<T> temp = stack.pop();
				root = temp.right;
				result.add(temp.value);
			}
		}
		
		return result;
	}
	
	public static class Tree<T> {
		private final Tree<T> left;
		private final T value;
		private final Tree<T> right;
		
		public Tree(final Tree<T> left, final T value, final Tree<T> right) {
			this.left = left;
			this.value = value;
			this.right = right;
		}
		
		public Tree<T> getLeft() {
			return this.left;
		}
		
		public T getValue() {
			return this.value;
		}
		
		public Tree<T> getRight() {
			return this.right;
		}
	}
}
