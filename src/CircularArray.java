import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CircularArray<T> implements Iterable<T> {
	private int startIndex;
	private final List<T> buffer;
	
	public CircularArray(int size) {
		this.startIndex = 0;
		this.buffer = new ArrayList<>();
		for(int index = 0; index < size; index++) {
			buffer.add(null);
		}
	}
	
	public T get(int index) {
		return this.buffer.get((this.startIndex + index) % this.buffer.size());
	}
	
	public void rotate(int index) {
		this.startIndex = (this.startIndex + index) % this.buffer.size();
	}
	
	public int size() {
		return this.buffer.size();
	}

	@Override
	public Iterator<T> iterator() {
		return new CircularArrayIterator<T>(this);
	}
	
	public static class CircularArrayIterator<T> implements Iterator<T> {

		private int count;
		private final CircularArray<T> circularArray;
		
		public CircularArrayIterator(CircularArray<T> circularArray) {
			this.count = 0;
			this.circularArray = circularArray;
		}
		
		@Override
		public boolean hasNext() {
			return this.count < this.circularArray.size();
		}

		@Override
		public T next() {
			final T value = this.circularArray.get(this.count);
			this.count++;
			return value;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
