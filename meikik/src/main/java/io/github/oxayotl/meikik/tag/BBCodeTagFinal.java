package io.github.oxayotl.meikik.tag;

/**
 * Implement this class to add a BBCode tag that cannot contain other BBCode
 * tags
 * 
 * @author oxayotl
 *
 */
public abstract class BBCodeTagFinal extends BBCodeTag {
	/**
	 * @return A regular expression that content of the tag must match.
	 */
	protected abstract String argumentRegexp();

	@Override
	public String findOpeningRegEx() {
		return "\\[" + shortName() + "](" + argumentRegexp() + ")\\[\\/" + shortName() + "]";
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
	public boolean selfContained() {
		return true;
	}

}
