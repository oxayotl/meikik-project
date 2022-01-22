package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * BBCode tag [url="https://example-url.com"]My text[/url] to turn "my text"
 * into a clickable url pointing toward example-url.com
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class UrlWithArgument extends BBCodeTag {
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

	@Override
	public boolean selfContained() {
		return false;
	}

}
