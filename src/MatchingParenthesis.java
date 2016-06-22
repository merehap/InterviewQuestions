import java.util.HashMap;
import java.util.Map;


public class MatchingParenthesis {
	
	public static boolean matching(final String input) {
		Map<Character, Character> matches = new HashMap<>();
		matches.put('(', ')');
		matches.put('{', '}');
		matches.put('[', ']');
		
		for(int index = 0; index < input.length() - 1; index += 2) {
			final char opener = input.charAt(index);
			final char closer = input.charAt(index + 1);
			if(!matches.containsKey(opener)
					|| matches.get(opener) != closer) {
				return false;
			}
		}
		
		return true;
	}
}
