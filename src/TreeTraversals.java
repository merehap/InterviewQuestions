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

	public static <T> List<T> levelOrderTraversal(Tree<T> tree) {
		List<T> result = new ArrayList<>();
		
		List<Tree<T>> currentLevel = new ArrayList<>();
		currentLevel.add(tree);
		
		while(!currentLevel.isEmpty()) {
			List<Tree<T>> nextLevel = new ArrayList<>();
			for(final Tree<T> node : currentLevel) {
				result.add(node.value);
				nextLevel.addAll(node.getChildren());
			}
			
			currentLevel = nextLevel;
		}
		
		return result;
	}
	
	public static <T> Tree<T> fromInOrderAndPostOrder(
			List<T> inorder, List<T> postorder) {
		
		if(inorder.size() <= 0) {
			return null;
		}
		
		T rootValue = postorder.get(postorder.size() - 1);
		
		int inorderRootIndex;
		for(inorderRootIndex = 0; inorderRootIndex < inorder.size(); inorderRootIndex++) {
			if(inorder.get(inorderRootIndex) == rootValue) {
				break;
			}
		}
		
		Tree<T> left = fromInOrderAndPostOrder(
				inorder.subList(0, inorderRootIndex),
				postorder.subList(0, inorderRootIndex));
		
		Tree<T> right = fromInOrderAndPostOrder(
				inorder.subList(inorderRootIndex + 1, inorder.size()),
				postorder.subList(inorderRootIndex, inorder.size() - 1));
		
		return new Tree<T>(
				left,
				rootValue,
				right);
	}
	
    public static boolean isHeightBalanced(Tree<T> tree) {
        if(Math.abs(depth(tree.left), depth(tree.right)) > 1) {
            return false;
        }

        return isHeightBalanced(tree.left) && isHeightBalanced(tree.right);
    }

    public static int depth(Tree<T> tree) {
        if(!tree.hasChildren()) {
            return 1;
        }

        return 1 + Math.max(depth(tree.left), depth(tree.right));
    }

	public static class Tree<T> {
		private final Tree<T> left;
		private final T value;
		private final Tree<T> right;
		
		public Tree(final T value) {
			this(null, value, null);
		}
		
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
		
		public List<Tree<T>> getChildren() {
			List<Tree<T>> children = new ArrayList<>();
			if(this.left != null) {
				children.add(this.left);
			}
			
			if(this.right != null) {
				children.add(this.right);
			}
			
			return children;
		}
		
		public boolean hasChildren() {
			return this.left != null || this.right != null;
		}
	}
}
