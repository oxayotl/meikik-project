package io.github.oxayotl.meikik.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * Utility class to load {@link io.github.oxayotl.meikik.tag.BBCodeTag
 * BBCodeTag}
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class BBCodeTagScanner {
	/**
	 * @param value         a string containing comma-separated BBCode shortnames
	 * @param availableTags List of all available BBCode tags
	 * @return the corresponding {@link io.github.oxayotl.meikik.tag.BBCodeTag
	 *         BBCodeTag}
	 */
	public static Collection<BBCodeTag> findTagsFromString(String value, Collection<BBCodeTag> availableTags) {
		if ("all".equals(value)) {
			return availableTags;
		}
		Collection<String> shortNames = Arrays.asList(value.split(","));
		return availableTags.stream().filter(tag -> shortNames.contains(tag.shortName())).collect(Collectors.toList());
	}
}
