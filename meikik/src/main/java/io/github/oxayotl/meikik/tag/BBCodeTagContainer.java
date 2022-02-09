package io.github.oxayotl.meikik.tag;

/**
 * Implement this class to add a BBCode tag that can contain other BBCode tags
 * 
 * @author oxayotl
 *
 */
public abstract class BBCodeTagContainer extends BBCodeTag {
	/**
	 * @return A regular expression that the argument must match if the BBCode tag
	 *         has an argument, or null if the tag has no argument.
	 */
	protected abstract String argumentRegexp();

	@Override
	public String findOpeningRegEx() {
		if (argumentRegexp() != null) {
			return "\\[" + shortName() + "=&quot;(" + argumentRegexp() + ")&quot;]";
		} else {
			return "\\[" + shortName() + "]";
		}
	}

	@Override
	public String findClosingTag() {
		return "[/" + shortName() + "]";
	}

	@Override
	public boolean selfContained() {
		return false;
	}

}
