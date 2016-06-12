import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Trie {

	private final String value;
	private final Map<String, Trie> children;
	
	public static Trie fromList(final List<String> inputs) {
		final Trie trie = new Trie("", new HashMap<String, Trie>());
		
		for(final String text : inputs) {
			for(int length = 1; length < text.length(); length++) {
				Trie parent = trie;
				Trie child;
				final String key = text.substring(0, length);
				if(parent.children.containsKey(key)) {
					child = parent.children.get(key);
				} else {
					child = new Trie(key, new HashMap<String, Trie>());
					parent.children.put(key, child);
				}
				
				parent = child;
			}
		}
		
		return trie;
	}
	
	public boolean startsWith(final String prefix) {
		Trie node = this;
		for(int index = 1; index < prefix.length(); index++) {
			if(!node.children.containsKey(prefix.substring(0, index))) {
				return false;
			}
			
			node = node.children.get(prefix.substring(0));
		}
		
		return true;
	}
	
	private Trie(final String value, final Map<String, Trie> children) {
		this.value = value;
		this.children = children;
	}
}
