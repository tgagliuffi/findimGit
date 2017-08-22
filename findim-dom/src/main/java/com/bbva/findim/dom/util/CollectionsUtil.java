package com.bbva.findim.dom.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class CollectionsUtil {

	private CollectionsUtil() {
	}

	public static List<String> stringToList(String list, String separator) {
		if (list == null) {
			return Collections.emptyList();
		}
		String[] splitedString = list.split(Pattern.quote(separator));
		return Arrays.asList(splitedString);
	}

}
