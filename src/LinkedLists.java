
public class LinkedLists {

//	Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
//	before all nodes greater than or equal to x . lf x is contained within the list, the values of x only need
//	to be after the elements less than x (see below) . The partition element x can appear anywhere in the
//	"right partition"; it does not need to appear between the left and right partitions.
//	EXAMPLE
//	Input: 3 -> 5 -> 8 -> 5 - > 10 -> 2 -> 1 [partition = 5)
//	Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
	public static LinkedListNode partition(LinkedListNode head, int value) {
		
		LinkedListNode result = null;
		
		LinkedListNode previous = null;
		LinkedListNode current = head;
		while(current != null) {
			if(current.value < value) {
				LinkedListNode next = current.next;
				
				if(result == null) {
					head = current;
					head.next = null;
				} else {
					current.next = head;
					head = current;
				}
				
				current = previous;
				current.next = next;
			}
			
			current = current.next;
		}
		
		return result == null ? head : result;
	}
	
	public static class LinkedListNode {
		private LinkedListNode next;
		private int value;
	}
}
