package io.github.oxayotl.meikik.tag;

public abstract class BBCodeTag {
	abstract public String findOpeningRegEx();

	abstract public String buildStartingHtml(String argument);

	abstract public String findClosingTag();

	abstract public String buildEndingHtml();

	abstract public String shortName();

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