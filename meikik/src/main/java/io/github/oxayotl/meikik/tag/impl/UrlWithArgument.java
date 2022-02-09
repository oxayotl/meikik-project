package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTagContainer;

/**
 * BBCode tag [url="https://example-url.com"]My text[/url] to turn "my text"
 * into a clickable url pointing toward example-url.com
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class UrlWithArgument extends BBCodeTagContainer {
	@Override
	public String shortName() {
		return "url";
	}

	@Override
	protected String argumentRegexp() {
		return "https?://[-a-zA-Z0-9@:%._\\+~#=/?&]+";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<a target=\"_blank\" href=\"" + argument + "\">";
	}

	@Override
	public String buildEndingHtml() {
		return "</a>";
	}

}
