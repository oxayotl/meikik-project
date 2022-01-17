package io.github.oxayotl.meikik.tag.impl;

import io.github.oxayotl.meikik.tag.BBCodeTag;

public class Code extends BBCodeTag {

	@Override
	public String findOpeningRegEx() {
		return "\\[code](.*?)\\[\\/code]";
	}

	@Override
	public String buildStartingHtml(String argument) {
		return "<pre>" + argument + "</pre>";
	}

	@Override
	public String findClosingTag() {
		return null;
	}

	@Override
	public String buildEndingHtml() {
		return null;
	}

	@Override
	public String shortName() {
		return "code";
	}

	@Override
	public boolean selfContained() {
		return true;
	}

}
