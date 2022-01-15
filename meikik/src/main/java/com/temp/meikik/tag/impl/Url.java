package com.temp.meikik.tag.impl;

import com.temp.meikik.tag.BBCodeTag;

public class Url extends BBCodeTag {
	static final String urlRegex = "https?://[-a-zA-Z0-9@:%._\\+~#=/?&]+";

	@Override
	public String findOpeningRegEx() {
		return "\\[url=&quot;(" + urlRegex + ")&quot;]";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<a target=\"_blank\" href=\"" + argument + "\">";
	}

	@Override
	public String findClosingTag() {
		return "[/url]";
	}

	@Override
	public String buildEndingHtml() {
		return "</a>";
	}

	@Override
	public String shortName() {
		return "url";
	}

}
