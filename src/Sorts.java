import java.util.ArrayList;
import java.util.List;


public class Sorts {
	public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
		
		if(list.size() < 2) {
			return list;
		}
		
		if(list.size() == 2) {
			if(list.get(0).compareTo(list.get(1)) > 0) {
				final T temp = list.get(0);
				list.set(0, list.get(1));
				list.set(1, temp);
			}
			
			return list;
		}
		
		List<T> left = mergeSort(list.subList(0, list.size() / 2));
		List<T> right = mergeSort(list.subList(list.size() / 2, list.size()));
		
		List<T> result = new ArrayList<>();
		int leftIndex = 0;
		int rightIndex = 0;
		while(leftIndex < left.size() || rightIndex < right.size()) {
			while(left.get(leftIndex).compareTo(right.get(rightIndex)) <= 0
					|| rightIndex >= right.size()) {
				result.add(left.get(leftIndex));
			}
			
			while(left.get(leftIndex).compareTo(right.get(rightIndex)) > 0
					|| leftIndex >= left.size()) {
				result.add(right.get(rightIndex));
			}
		}
		
		return result;
	}
}
