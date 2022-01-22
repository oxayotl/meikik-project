package io.github.oxayotl.meikik.processor.impl;

import java.util.List;

import org.thymeleaf.expression.Strings;

import io.github.oxayotl.meikik.processor.AbstractTextAttributeTagProcessor;
import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * Process the 'basic' attribute, by replacing new lines by <br />
 * tags
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class BasicFormatAttributeTagProcessor extends AbstractTextAttributeTagProcessor {

	private static final String ATTR_NAME = "basic";

	/**
	 * @param dialectPrefix Prefix to be applied to name for matching
	 */
	public BasicFormatAttributeTagProcessor(final String dialectPrefix) {
		super(dialectPrefix, ATTR_NAME, 1000);
	}

	@Override
	protected String processString(String attributeContent, List<BBCodeTag> tags) {
		String escapedString = new Strings(null).escapeXml(attributeContent);
		return escapedString.replace(System.getProperty("line.separator"), "<br />");
	}

}