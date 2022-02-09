package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTagFinal;

/**
 * BBCode tag [code]text[/code] to write text in monospace font
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class Code extends BBCodeTagFinal {
	@Override
	protected String argumentRegexp() {
		return ".*?";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<pre>" + argument + "</pre>";
	}

	@Override
	public String shortName() {
		return "code";
	}
}
