package io.github.oxayotl.meikik.processor;

import java.util.Collection;

import org.thymeleaf.expression.Strings;

import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * Process the 'bbcode' attribute
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class BBCodeAttributeTagProcessor extends UBBCodeAttributeTagProcessor {

	private static final String ATTR_NAME = "text";

	/**
	 * @param dialectPrefix dialectPrefix Prefix to be applied to name for matching
	 * @param defaultTags   Comma-separated list of shortnames for tags to be
	 *                      processed by default
	 * @param availableTags Collection of one instance for each custom
	 *                      BBCodeTag-extending classes that should be available in
	 *                      the dialect
	 */
	public BBCodeAttributeTagProcessor(final String dialectPrefix, String defaultTags,
			Collection<BBCodeTag> availableTags) {
		super(dialectPrefix, defaultTags, availableTags, ATTR_NAME);
	}

	/**
	 * Function that will return the html string to be put inside the processed tag
	 * 
	 * @param attributeContent Already parsed content of the processed attribute
	 * @param tags             List of BBCode tags allowed. If null, then the
	 *                         default tags will be allowed instead
	 * @return The string that will compose the body of the processed tag
	 */
	protected String processString(String attributeContent, Collection<BBCodeTag> tags) {
		String original = new Strings(null).escapeXml(attributeContent);

		return super.processString(original, tags);
	}
}