import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LruCache<K, V> {
	
	private final int maxSize;
	private final Map<K, V> mappings;
	private final LinkedList<V> orderings;
	
	public LruCache(final int maxSize) {
		this.maxSize = maxSize;
		this.mappings = new HashMap<>();
		this.orderings = new LinkedList<>();
	}
	
	public V get(final K key) {
		final V value = this.mappings.get(key);
		this.orderings.remove(value);
		this.orderings.addFirst(value);
		return value;
	}
	
	public void set(final K key, final V value) {
		
		if(this.mappings.containsKey(key)) {
			this.mappings.remove(key);
			this.orderings.remove(value);
		}
		
		if(this.mappings.size() >= this.maxSize) {
			final V leastRecent = this.orderings.removeLast();
			mappings.values().remove(leastRecent);
		}
		
		this.mappings.put(key, value);
		this.orderings.addFirst(value);
	}
}
