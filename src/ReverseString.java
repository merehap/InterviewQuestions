
public class ReverseString {
	public static String reverseString(final String input) {
		char[] output = new char[input.length()];
		for (int index = 0; index < input.length(); index++) {
			output[input.length() - index - 1] = input.charAt(index);
		}
		
		return new String(output);
	}
	
	public static void reverseCharsInPlace(char[] input) {
		for (int index = 0; index < input.length; index++) {
			final char temp = input[index];
			input[index] = input[input.length - index - 1];
			input[input.length - index - 1] = temp;
		}
	}
}
