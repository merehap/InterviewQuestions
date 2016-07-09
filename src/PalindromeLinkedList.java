public class PalindromeLinkedList {
    public static <T> boolean isPalindrome(LinkedList<T> list) {
        LinkedList<T> reversed = new LinkedList<>();
        for(T t : list) {
            reversed.addFirst(t);
        }

        while(!list.isEmpty()) {
            if(list.pop() != reversed.pop()) {
                return false;
            }
        }

        return true;
    }
}
