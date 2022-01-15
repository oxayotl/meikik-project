package io.github.oxayotl.meikik.domain;

public class TagReplacement {
	private final String replacement;
	private final String argument;
	private final int replacedCharacters;

	public TagReplacement(String replacement, int replacedCharacters, String argument) {
		this.replacement = replacement;
		this.argument = argument;
		this.replacedCharacters = replacedCharacters;
	}

	public String getReplacement() {
		return replacement;
	}

	public int getReplacedCharacters() {
		return replacedCharacters;
	}

	public String getArgument() {
		return argument;
	}
}
