import java.util.Arrays;


public class RotateArray {
	public static <T> T[] rotateArray(
			final T[] input,
			final int offset) {
		
		T[] output = Arrays.copyOf(input, input.length);
		for(int index = 0; index < input.length; index++) {
			output[(index + offset) % input.length] = input[index];
		}
		
		return output;
	}
}
