package com.temp.meikik.processor.impl;

import java.util.List;

import org.thymeleaf.expression.Strings;

import com.temp.meikik.processor.AbstractTextAttributeTagProcessor;
import com.temp.meikik.tag.BBCodeTag;

public class BoldAndItalicAndLinksFormatAttributeTagProcessor extends AbstractTextAttributeTagProcessor {

	private static final String ATTR_NAME = "bbcode";

	public BoldAndItalicAndLinksFormatAttributeTagProcessor(final String dialectPrefix) {
		super(dialectPrefix, ATTR_NAME);
	}

	@Override
	protected String processString(String attributeContent, List<BBCodeTag> tags) {
		String escapedString = new Strings(null).escapeXml(attributeContent);
		String lineBreakString = escapedString.replace(System.getProperty("line.separator"), "<br />");
		String formatedString = lineBreakString.replaceAll("\\[i](.*?)\\[/i]", "<i>$1</i>")
				.replaceAll("\\[b](.*?)\\[/b]", "<b>$1</b>");
		String result = formatedString.replaceAll("\\[url=&quot;(.*?)&quot;](.*?)\\[/url]",
				"<a target=\"_blank\" href=\"$1\">$2</a>");
		return result;
	}

}