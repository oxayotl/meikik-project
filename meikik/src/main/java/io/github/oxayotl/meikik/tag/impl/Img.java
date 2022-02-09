package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTagFinal;

/**
 * BBCode tag [img]https://example.com/picture.png[/img] to make an url pointing
 * toward an image file into an html &lt;img&gt; element
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Img extends BBCodeTagFinal {
	@Override
	protected String argumentRegexp() {
		return "https?://[-a-zA-Z0-9@:%._\\+~#=/?&]+";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<img src=\"" + argument + "\" />";
	}

	@Override
	public String shortName() {
		return "img";
	}

}
