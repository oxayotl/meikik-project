package com.temp.meikik.processor.impl;

import java.util.List;

import org.thymeleaf.expression.Strings;

import com.temp.meikik.processor.AbstractTextAttributeTagProcessor;
import com.temp.meikik.tag.BBCodeTag;

public class BasicFormatAttributeTagProcessor extends AbstractTextAttributeTagProcessor {

	private static final String ATTR_NAME = "basic";

	public BasicFormatAttributeTagProcessor(final String dialectPrefix) {
		super(dialectPrefix, ATTR_NAME);
	}

	@Override
	protected String processString(String attributeContent, List<BBCodeTag> tags) {
		String escapedString = new Strings(null).escapeXml(attributeContent);
		return escapedString.replace(System.getProperty("line.separator"), "<br />");
	}

}