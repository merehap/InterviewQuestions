import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Trie {

	private final String value;
	private final Map<String, Trie> children;
	private boolean isWord;
	
	public static Trie fromList(final List<String> inputs) {
		final Trie trie = new Trie("", new HashMap<String, Trie>());
		
		for(final String text : inputs) {
			trie.insert(text);
		}
		
		return trie;
	}
	
	public void insert(final String text) {
		Trie child = null;
		for(int length = 1; length < text.length(); length++) {
			Trie parent = this;
			final String key = text.substring(0, length);
			if(parent.children.containsKey(key)) {
				child = parent.children.get(key);
			} else {
				child = new Trie(key, new HashMap<String, Trie>());
				parent.children.put(key, child);
			}
			
			parent = child;
		}
		
		child.isWord = true;
	}
	
	public boolean search(final String text) {
		return this.search(text, true);
	}
	
	public boolean startsWith(final String prefix) {
		return this.search(prefix, false);
	}
	
	private boolean search(final String prefix, boolean mustBeWord) {
		Trie node = this;
		for(int index = 1; index < prefix.length(); index++) {
			if(!node.children.containsKey(prefix.substring(0, index))) {
				return false;
			}
			
			node = node.children.get(prefix.substring(0));
		}
		
		return mustBeWord ? node.isWord : true;
	}
	
	private Trie(final String value, final Map<String, Trie> children) {
		this.value = value;
		this.children = children;
	}
}
