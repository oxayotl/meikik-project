package com.temp.meikik.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.temp.meikik.domain.Tag;
import com.temp.meikik.domain.TagReplacement;
import com.temp.meikik.tag.BBCodeTag;

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

	/**
	 * Find the regular expression identifiying the opening tag.
	 * 
	 * @param id the identifier.
	 * @return the enum.
	 */
	public static String findOpeningRegEx(Tag tag) {

		switch (tag) {
		case B:
			return "\\[b]";
		case I:
			return "\\[i]";
		case URL:
			return "\\[url=&quot;(" + urlRegex + ")&quot;]";
		default:
			throw new RuntimeException("Unknown tag: " + tag);
		}
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
