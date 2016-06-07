import java.util.LinkedList;


public class LinkedListDuplicates {
	
	public static <T> void removeDuplicates(LinkedList<T> list) {
		
		for(int index = list.size() - 1; index > 0; index--) {
			if(list.get(index) == list.get(index - 1)) {
				list.remove(index);
			}
				
		}
	}
}
