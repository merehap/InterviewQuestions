
public class AddDigits {
	
	public static int addDigits(final Integer n) {
		
		int sum = 0;
		for(final char digit : n.toString().toCharArray()) {
			sum += Character.getNumericValue(digit);
		}
		
		if(sum < 10) {
			return sum;
		}
		
		return addDigits(sum);
	}
}
