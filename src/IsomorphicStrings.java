import java.util.HashMap;
import java.util.Map;


public class IsomorphicStrings {
	public static boolean areIsomorphic(final String a, final String b) {
		
		if(a.length() != b.length()) {
			return false;
		}
		
		Map<Character, Character> mappings = new HashMap<>();
		for(int index = 0; index < a.length(); index++) {
			final Character c1 = a.charAt(index);
			final Character c2 = b.charAt(index);
			
			final Character expectedC2 = mappings.get(c1);
			if(expectedC2 == null) {
				if(mappings.containsValue(c2)) {
					return false;
				}
				
				mappings.put(c1, c2);
			} else if (expectedC2 != c2) {
				return false;
			}
		}
		
		return true;
	}
}
