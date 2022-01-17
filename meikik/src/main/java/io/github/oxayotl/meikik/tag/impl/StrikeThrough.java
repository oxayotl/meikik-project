package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

public class StrikeThrough extends BBCodeTag {

	@Override
	public String findOpeningRegEx() {
		return "\\[s]";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<del>";
	}

	@Override
	public String findClosingTag() {
		return "[/s]";
	}

	@Override
	public String buildEndingHtml() {
		return "</del>";
	}

	@Override
	public String shortName() {
		return "s";
	}

	@Override
	public boolean selfContained() {
		return false;
	}

}
