package dictionary;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class UserInterface {
	// ("\\b(?:UI|PRI|RET)\\b"
	private static final String commandPattern = "\\b(create|read|p|s|i|r|exit)\\b";
	public static boolean isValid(String s) {
		Pattern regex = Pattern.compile(commandPattern);
		Matcher matcher = regex.matcher(s);
		return matcher.find();
	}
}
