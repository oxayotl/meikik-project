package io.github.oxayotl.meikik.domain;

public enum Tag {

	I, B, URL;

	/**
	 * Parse the identifier and delivers the enum.
	 * 
	 * @param id the identifier.
	 * @return the enum.
	 */
	public static Tag parse(String id) {
		if (id == null) {
			return null;
		}
		switch (id) {
		case "i":
			return I;
		case "b":
			return B;
		case "url":
			return URL;
		default:
			return null;
		}
	}

}
