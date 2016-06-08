


public class Main {
	public static void main(String[] args) {
		TreeTraversals.Tree<Integer> tree = new TreeTraversals.Tree<Integer>(
				new TreeTraversals.Tree<>(
						new TreeTraversals.Tree<>(
								null,
								3,
								new TreeTraversals.Tree<>(
										null,
										4,
										null)),
						2,
						new TreeTraversals.Tree<>(
								null,
								5,
								null)),
				1,
				new TreeTraversals.Tree<>(
						new TreeTraversals.Tree<>(
								null,
								7,
								null),
						6,
						new TreeTraversals.Tree<>(
								null,
								8,
								null)));
		
		for(int value : TreeTraversals.iterative_preorder(tree)) {
			System.out.println(value);
		}
	}
}