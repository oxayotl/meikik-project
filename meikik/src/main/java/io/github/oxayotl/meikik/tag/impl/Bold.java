package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * BBCode tag [b]text[/b] to write text in bold
 *
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Bold extends BBCodeTag {

	@Override
	public String findOpeningRegEx() {
		return "\\[b]";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<b>";
	}

	@Override
	public String findClosingTag() {
		return "[/b]";
	}

	@Override
	public String buildEndingHtml() {
		return "</b>";
	}

	@Override
	public String shortName() {
		return "b";
	}

	@Override
	public boolean selfContained() {
		return false;
	}

}
