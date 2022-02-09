package io.github.oxayotl.meikik.processor;

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

import io.github.oxayotl.meikik.tag.BBCodeTag;
import io.github.oxayotl.meikik.utils.BBCodeTagScanner;

/**
 * Abstract method to deal with Spring expression parsing and set up generic
 * configuration
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public abstract class AbstractTextAttributeTagProcessor extends AbstractAttributeTagProcessor {

	/**
	 * Constructor that set up generic configuration for the processor
	 * 
	 * @param dialectPrefix Prefix to be applied to name for matching
	 * @param attributeName Name of the attribute that will be matched
	 * @param precedence    Precedence (inside dialect's own precedence)
	 */
	public AbstractTextAttributeTagProcessor(final String dialectPrefix, String attributeName, int precedence) {
		super(TemplateMode.HTML, // This processor will apply only to HTML mode
				dialectPrefix, // Prefix to be applied to name for matching
				null, // No tag name: match any tag name
				false, // No prefix to be applied to tag name
				attributeName, // Name of the attribute that will be matched
				true, // Apply dialect prefix to attribute name
				precedence, // Precedence (inside dialect's own precedence)
				true); // Remove the matched attribute afterwards
	}

	/**
	 * Function that will return the html string to be put inside the processed tag
	 * 
	 * @param attributeContent Already parsed content of the processed attribute
	 * @param tags             List of BBCode tags allowed. If null, then the
	 *                         default tags will be allowed instead
	 * @return The string that will compose the body of the processed tag
	 */
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
		String value = tag.getAttributeValue(getDialectPrefix(), "allowed-tags");
		List<BBCodeTag> tags = null;
		if (value != null) {
			tags = BBCodeTagScanner.findTagsFromString(value);
		}
		structureHandler.setBody(processString(content, tags), false);
	}

}