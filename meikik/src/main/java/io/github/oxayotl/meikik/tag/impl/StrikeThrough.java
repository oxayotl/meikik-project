package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTagContainer;

/**
 * BBCode tag [s]text[/s] to write striked through text
 *
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class StrikeThrough extends BBCodeTagContainer {
	@Override
	public String shortName() {
		return "s";
	}

	@Override
	protected String argumentRegexp() {
		return null;
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<del>";
	}

	@Override
	public String buildEndingHtml() {
		return "</del>";
	}
}
