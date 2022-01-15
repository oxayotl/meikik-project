package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

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

}
