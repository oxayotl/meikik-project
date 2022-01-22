package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * BBCode tag [img]https://example.com/picture.png[/img] to make an url pointing
 * toward an image file into an html &lt;img&gt; element
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Img extends BBCodeTag {
	static final String urlRegex = "https?://[-a-zA-Z0-9@:%._\\+~#=/?&]+";

	@Override
	public String findOpeningRegEx() {
		return "\\[img](" + urlRegex + ")\\[\\/img]";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<img src=\"" + argument + "\" />";
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
		return "img";
	}

	@Override
	public boolean selfContained() {
		return true;
	}

}
