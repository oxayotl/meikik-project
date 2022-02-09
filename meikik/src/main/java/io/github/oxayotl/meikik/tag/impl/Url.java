package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTagFinal;

/**
 * BBCode tag [url]https://example-url.com[/url] to make an url into a clickable
 * link
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Url extends BBCodeTagFinal {
	@Override
	protected String argumentRegexp() {
		return "https?://[-a-zA-Z0-9@:%._\\+~#=/?&]+";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<a target=\"_blank\" href=\"" + argument + "\">" + argument + "</a>";
	}

	@Override
	public String shortName() {
		return "url";
	}

}
