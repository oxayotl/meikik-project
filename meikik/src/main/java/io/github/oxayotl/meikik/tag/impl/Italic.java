package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * BBCode tag [i]text[/i] to write text in italics
 *
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Italic extends BBCodeTag {

	@Override
	public String findOpeningRegEx() {
		return "\\[i]";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<i>";
	}

	@Override
	public String findClosingTag() {
		return "[/i]";
	}

	@Override
	public String buildEndingHtml() {
		return "</i>";
	}

	@Override
	public String shortName() {
		return "i";
	}

	@Override
	public boolean selfContained() {
		return false;
	}

}
