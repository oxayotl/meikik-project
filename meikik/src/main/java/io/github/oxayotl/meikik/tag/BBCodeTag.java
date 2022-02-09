package io.github.oxayotl.meikik.tag;

/**
 * Implement this class to add support for a new BBCode tag
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public abstract class BBCodeTag {
	/**
	 * @return A regular expression that matches this BBCode opening tag, or this
	 *         entire BBCode tag if {@link #selfContained()} is true. If the tag
	 *         accepts an argument, it must be contained within the first group.
	 *         Note that this regular expression is matched against an html-escaped
	 *         string
	 */
	abstract public String findOpeningRegEx();

	/**
	 * @param argument The argument present in the parsed BBCode tag
	 * @return The opening HTML tag corresponding to BBCode tag, or the entire HTML
	 *         code if {@link #selfContained()} is true
	 */
	abstract public String buildStartingHtml(String argument);

	/**
	 * @return A regular expression that matches this BBCode end tag, or null if
	 *         {@link #selfContained()} is true
	 */
	abstract public String findClosingTag();

	/**
	 * @return The closing HTML tag corresponding to BBCode tag, or null if
	 *         {@link #selfContained()} is true
	 */
	abstract public String buildEndingHtml();

	/**
	 * @return The string representation of this tag, for allowed tag selection
	 */
	abstract public String shortName();

	/**
	 * @return Whether this BBCode tag can contain other BBCode tag or not
	 */
	abstract public boolean selfContained();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}