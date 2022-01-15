package com.temp.meikik.processor;

import java.util.List;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.temp.meikik.tag.BBCodeTag;
import com.temp.meikik.urils.Utils;

public abstract class AbstractTextAttributeTagProcessor extends AbstractAttributeTagProcessor {
	private static final int PRECEDENCE = 10000;

	public AbstractTextAttributeTagProcessor(final String dialectPrefix, String attributeName) {
		super(TemplateMode.HTML, // This processor will apply only to HTML mode
				dialectPrefix, // Prefix to be applied to name for matching
				null, // No tag name: match any tag name
				false, // No prefix to be applied to tag name
				attributeName, // Name of the attribute that will be matched
				true, // Apply dialect prefix to attribute name
				PRECEDENCE, // Precedence (inside dialect's own precedence)
				true); // Remove the matched attribute afterwards
	}

	protected abstract String processString(String attributeContent, List<BBCodeTag> tags);

	@Override
	protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
			final AttributeName attributeName, final String attributeValue,
			final IElementTagStructureHandler structureHandler) {

		final IEngineConfiguration configuration = context.getConfiguration();

		/*
		 * Obtain the Thymeleaf Standard Expression parser
		 */
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);

		/*
		 * Parse the attribute value as a Thymeleaf Standard Expression
		 */
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);

		/*
		 * Execute the expression just parsed
		 */
		final String content = (String) expression.execute(context);
		String value = tag.getAttributeValue(getDialectPrefix(), "balises");
		List<BBCodeTag> tags = null;
		if (value != null) {
			tags = Utils.parseTagString(value);
		}
		structureHandler.setBody(processString(content, tags), false);
	}

}