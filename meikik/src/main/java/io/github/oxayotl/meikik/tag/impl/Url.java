package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * BBCode tag [url]https://example-url.com[/url] to make an url into a clickable
 * link
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Url extends BBCodeTag {
	static final String urlRegex = "https?://[-a-zA-Z0-9@:%._\\+~#=/?&]+";

	@Override
	public String findOpeningRegEx() {
		return "\\[url](" + urlRegex + ")\\[\\/url]";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<a target=\"_blank\" href=\"" + argument + "\">" + argument + "</a>";
	}

	@Override
	public String findClosingTag() {
		return null;
	}

	@Override
	public String buildEndingHtml() {
		return null;
	}

	@Override
	public String shortName() {
		return "url";
	}

	@Override
	public boolean selfContained() {
		return true;
	}

}
