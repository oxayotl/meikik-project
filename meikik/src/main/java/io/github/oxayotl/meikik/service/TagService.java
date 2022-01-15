package io.github.oxayotl.meikik.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.oxayotl.meikik.domain.TagReplacement;
import io.github.oxayotl.meikik.tag.BBCodeTag;

public class TagService {
	static final String urlRegex = "https?://[-a-zA-Z0-9@:%._\\+~#=/?&]+";

	public static TagReplacement processOpeningRegEx(BBCodeTag tag, String string) {
		String regex = tag.findOpeningRegEx();
		Pattern p = Pattern.compile("(" + regex + ")");
		Matcher m = p.matcher(string);
		if (m.lookingAt()) {
			String argument = m.groupCount() == 2 ? m.group(2) : null;
			String replacement = tag.buildStartingHtml(argument);
			int replacedCharacters = m.end(1);
			return new TagReplacement(replacement, replacedCharacters, argument);
		}
		return null;
	}

	public static TagReplacement processEndRegEx(BBCodeTag bbTag, String string) {
		String s = bbTag.findClosingTag();
		if (string.startsWith(s)) {
			int replacedCharacters = s.length();
			String replacement = bbTag.buildEndingHtml();
			return new TagReplacement(replacement, replacedCharacters, null);
		}
		return null;
	}

}
