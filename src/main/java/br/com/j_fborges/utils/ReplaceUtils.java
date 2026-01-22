package br.com.j_fborges.utils;

public class ReplaceUtils {
	
	public static String replace(String value, String ...patterns) {
		String stringToNormalize = value;
		for (String pattern : patterns) {
			stringToNormalize = stringToNormalize.replace(pattern, "");
		}
		return stringToNormalize;
	}
}
