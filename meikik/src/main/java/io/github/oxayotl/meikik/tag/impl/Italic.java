package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTagContainer;

/**
 * BBCode tag [i]text[/i] to write text in italics
 *
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Italic extends BBCodeTagContainer {
	@Override
	public String shortName() {
		return "i";
	}

	@Override
	protected String argumentRegexp() {
		return null;
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<i>";
	}

	@Override
	public String buildEndingHtml() {
		return "</i>";
	}
}
