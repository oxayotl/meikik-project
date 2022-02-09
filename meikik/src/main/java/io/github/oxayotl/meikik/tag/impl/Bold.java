package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTagContainer;

/**
 * BBCode tag [b]text[/b] to write text in bold
 *
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Bold extends BBCodeTagContainer {

	@Override
	public String shortName() {
		return "b";
	}

	@Override
	protected String argumentRegexp() {
		return null;
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<b>";
	}

	@Override
	public String buildEndingHtml() {
		return "</b>";
	}
}
