package io.github.oxayotl.meikik.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import io.github.oxayotl.meikik.domain.TagReplacement;
import io.github.oxayotl.meikik.tag.BBCodeTag;
import io.github.oxayotl.meikik.utils.BBCodeTagScanner;

/**
 * Process the 'bbcode' attribute
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class UBBCodeAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private Collection<BBCodeTag> availableTags;
	private Collection<BBCodeTag> defaultTags;
	private static final String ATTR_NAME = "utext";

	/**
	 * @param dialectPrefix dialectPrefix Prefix to be applied to name for matching
	 * @param defaultTags   Comma-separated list of shortnames for tags to be
	 *                      processed by default
	 * @param availableTags Collection of one instance for each custom
	 *                      BBCodeTag-extending classes that should be available in
	 *                      the dialect
	 */
	public UBBCodeAttributeTagProcessor(final String dialectPrefix, String defaultTags,
			Collection<BBCodeTag> availableTags) {
		this(dialectPrefix, defaultTags, availableTags, ATTR_NAME);
	}

	protected UBBCodeAttributeTagProcessor(final String dialectPrefix, String defaultTags,
			Collection<BBCodeTag> availableTags, String attrName) {
		super(TemplateMode.HTML, // This processor will apply only to HTML mode
				dialectPrefix, // Prefix to be applied to name for matching
				null, // No tag name: match any tag name
				false, // No prefix to be applied to tag name
				attrName, // Name of the attribute that will be matched
				true, // Apply dialect prefix to attribute name
				2000, // Precedence (inside dialect's own precedence)
				true); // Remove the matched attribute afterwards
		this.availableTags = availableTags;
		this.defaultTags = BBCodeTagScanner.findTagsFromString(defaultTags, this.availableTags);
	}

	private BBCodeTag findOpeningTag(String substring, Collection<BBCodeTag> tags,
			List<BBCodeTag> currentlyOpenedTags) {
		List<BBCodeTag> potentialTags = new ArrayList<>(tags);
		potentialTags.removeAll(currentlyOpenedTags);
		for (BBCodeTag tag : potentialTags) {
			String regex = tag.findOpeningRegEx();
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(substring);
			if (m.lookingAt()) {
				return tag;
			}
		}
		return null;
	}

	private BBCodeTag findClosingTag(String substring, List<BBCodeTag> currentlyOpenedTags) {
		for (BBCodeTag tag : currentlyOpenedTags) {
			if (substring.startsWith(tag.findClosingTag())) {
				return tag;
			}
		}
		return null;
	}

	private TagReplacement processOpeningRegEx(BBCodeTag tag, String string) {
		String regex = tag.findOpeningRegEx();
		Pattern p = Pattern.compile("(" + regex + ")");
		Matcher m = p.matcher(string);
		if (m.lookingAt()) {
			String argument = m.groupCount() == 2 ? m.group(2) : null;
			String replacement = tag.buildStartingHtml(argument);
			int replacedCharacters = m.end(1);
			return new TagReplacement(replacement, replacedCharacters);
		}
		return null;
	}

	private TagReplacement processEndRegEx(BBCodeTag bbTag, String string) {
		String s = bbTag.findClosingTag();
		if (string.startsWith(s)) {
			int replacedCharacters = s.length();
			String replacement = bbTag.buildEndingHtml();
			return new TagReplacement(replacement, replacedCharacters);
		}
		return null;
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
		if (tags == null) {
			tags = defaultTags;
		}
		final String original = attributeContent;

		StringBuilder result = new StringBuilder();
		int lastTagIndex = 0;
		List<BBCodeTag> currentlyOpenedTags = new ArrayList<>();
		List<String> currentlyOpenedTagsHtml = new ArrayList<>();
		for (int i = 0; i < original.length(); i++) {
			if (original.charAt(i) == '[') {
				if (original.charAt(i + 1) == '/') {
					BBCodeTag closingTag = findClosingTag(original.substring(i), currentlyOpenedTags);
					if (closingTag != null) {
						result.append(original.substring(lastTagIndex, i));
						TagReplacement tr = processEndRegEx(closingTag, original.substring(i));
						i += tr.getReplacedCharacters() - 1;
						lastTagIndex = i + 1;
						int j;
						for (j = currentlyOpenedTags.size() - 1; currentlyOpenedTags.get(j) != closingTag; j--) {
							result.append(currentlyOpenedTags.get(j).buildEndingHtml());
						}
						result.append(currentlyOpenedTags.get(j).buildEndingHtml());
						currentlyOpenedTags.remove(j);
						currentlyOpenedTagsHtml.remove(j);
						for (; j < currentlyOpenedTags.size(); j++) {
							result.append(currentlyOpenedTagsHtml.get(j));
						}
					}

				} else {
					BBCodeTag openedTag = findOpeningTag(original.substring(i), tags, currentlyOpenedTags);
					if (openedTag != null) {
						result.append(original.substring(lastTagIndex, i));
						TagReplacement tr = processOpeningRegEx(openedTag, original.substring(i));
						i += tr.getReplacedCharacters() - 1;
						lastTagIndex = i + 1;
						result.append(tr.getReplacement());
						if (!openedTag.selfContained()) {
							currentlyOpenedTags.add(openedTag);
							currentlyOpenedTagsHtml.add(tr.getReplacement());
						}
					}
				}
			}
		}
		result.append(original.substring(lastTagIndex));

		while (!currentlyOpenedTags.isEmpty()) {
			Collections.reverse(currentlyOpenedTagsHtml);
			currentlyOpenedTags.forEach(tag -> result.append(tag.buildEndingHtml()));
		}

		return result.toString();

	}

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
		Collection<BBCodeTag> tags = null;
		if (value != null) {
			tags = BBCodeTagScanner.findTagsFromString(value, this.availableTags);
		}
		structureHandler.setBody(processString(content, tags), false);
	}

}