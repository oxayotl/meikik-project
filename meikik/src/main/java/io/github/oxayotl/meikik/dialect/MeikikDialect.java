package io.github.oxayotl.meikik.dialect;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.oxayotl.meikik.processor.BBCodeAttributeTagProcessor;
import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * The Meikik text dialog, for user-inputed text with or without BBCode
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class MeikikDialect extends AbstractProcessorDialect {

	private static final String DIALECT_NAME = "Meikik Dialect";
	private String defaultTags;
	private Collection<BBCodeTag> availableTags;

	/**
	 * @param tags          List of tags that are authorized by default when using
	 *                      the
	 *                      {@link io.github.oxayotl.meikik.processor.BBCodeAttributeTagProcessor
	 *                      bbcode} attribute
	 * @param availableTags List of all available BBCode tags
	 */
	public MeikikDialect(String tags, Collection<BBCodeTag> availableTags) {
		// We will set this dialect the same "dialect processor" precedence as
		// the Standard Dialect, so that processor executions can interleave.
		super(DIALECT_NAME, "text", StandardDialect.PROCESSOR_PRECEDENCE);
		this.defaultTags = tags;
		this.availableTags = availableTags;
	}

	@Override
	public Set<IProcessor> getProcessors(final String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new BBCodeAttributeTagProcessor(dialectPrefix, defaultTags, availableTags));
		// This will remove the xmlns:score attributes we might add for IDE validation
		processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
		return processors;
	}

}
