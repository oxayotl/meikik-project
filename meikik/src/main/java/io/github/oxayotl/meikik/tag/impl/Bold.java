package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

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

}
