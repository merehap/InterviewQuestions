
public class LengthOfLastWord {
	
	public static int lengthOfLastWord(String text) {
		if(text.isEmpty()) {
			return 0;
		}
		
		int wordEnd = text.length() - 1;
		
		while(text.charAt(wordEnd) == ' ') {
			wordEnd--;
		}
		
		int wordStart;
		for(wordStart = wordEnd; text.charAt(wordStart) != ' '; wordStart--) {
			
		}
		
		return wordEnd - wordStart + 1;
	}
}
