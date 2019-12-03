package console.swinggamingconsole.util;

/**
 * Just a class with methods for string manipulation and utilities.
 * 
 * @author Tyler Buchanan <t.buchananx97@gmail.com>
 */
public final class StringUtils {

	/**
	 * A method that outputs a string that has been repeated a certain amount of
	 * times.
	 * 
	 * @param toRepeat
	 *            The string we are repeating.
	 * @param repeatAmount
	 *            The amount of times we repeat the string.
	 * @return The new, repeated string.
	 */
	public static String repeat(String toRepeat, int repeatAmount) {
		String newString = toRepeat;
		for (int i = 1; i <= repeatAmount; i++) {
			newString = newString.concat(toRepeat);
		}
		return newString;
	}
}