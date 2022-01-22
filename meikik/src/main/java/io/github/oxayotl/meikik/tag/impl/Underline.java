package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * BBCode tag [u]text[/u] to underline text
 *
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Underline extends BBCodeTag {

	@Override
	public String findOpeningRegEx() {
		return "\\[u]";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<ins>";
	}

	@Override
	public String findClosingTag() {
		return "[/u]";
	}

	@Override
	public String buildEndingHtml() {
		return "</ins>";
	}

	@Override
	public String shortName() {
		return "u";
	}

	@Override
	public boolean selfContained() {
		return false;
	}

}
