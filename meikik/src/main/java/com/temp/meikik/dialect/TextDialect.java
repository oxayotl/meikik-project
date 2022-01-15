package com.temp.meikik.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import com.temp.meikik.processor.impl.BBCodeAttributeTagProcessor;
import com.temp.meikik.processor.impl.BasicFormatAttributeTagProcessor;

public class TextDialect extends AbstractProcessorDialect {

	private static final String DIALECT_NAME = "Text Dialect";
	private String defaultTags;

	public TextDialect(String tags) {
		// We will set this dialect the same "dialect processor" precedence as
		// the Standard Dialect, so that processor executions can interleave.
		super(DIALECT_NAME, "text", StandardDialect.PROCESSOR_PRECEDENCE);
		this.defaultTags = tags;
	}

	public Set<IProcessor> getProcessors(final String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new BasicFormatAttributeTagProcessor(dialectPrefix));
		processors.add(new BBCodeAttributeTagProcessor(dialectPrefix, defaultTags));
//		processors.add(new BoldAndItalicFormatAttributeTagProcessor(dialectPrefix));
//		processors.add(new BoldAndItalicAndLinksFormatAttributeTagProcessor(dialectPrefix));
		// This will remove the xmlns:score attributes we might add for IDE validation
		processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
		return processors;
	}

}
