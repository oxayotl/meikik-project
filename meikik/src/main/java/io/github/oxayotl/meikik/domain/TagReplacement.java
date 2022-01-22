package io.github.oxayotl.meikik.domain;

/**
 * Describe the replacement of a BBCode tag with an HTML tag
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class TagReplacement {
	private final String replacement;
	private final int replacedCharacters;

	/**
	 * @param replacement        The resulting HTML string
	 * @param replacedCharacters The number of characters in the BBCode string that
	 *                           was replaced
	 */
	public TagReplacement(String replacement, int replacedCharacters) {
		this.replacement = replacement;
		this.replacedCharacters = replacedCharacters;
	}

	/**
	 * @return The HTML string resulting from this tag replacement
	 */
	public String getReplacement() {
		return replacement;
	}

	/**
	 * @return The number of characters in the BBCode string that was replaced
	 */
	public int getReplacedCharacters() {
		return replacedCharacters;
	}

}
