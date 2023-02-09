package io.github.oxayotl.meikik.tag;

import org.springframework.stereotype.Component;

/**
 * We are extending BBCodeTagContainer rather than BBCodeTagFinal because the
 * [quote] tag can include other BBCode tags, for instance [quote="Meikik;1"]I'm
 * [b]yelling[/b][/quote]
 */
@Component
public class Quote extends BBCodeTagContainer {
	/**
	 * We check check for an opening tag [quote="Username;messageId"], were
	 * messageId is a number
	 */
	@Override
	protected String argumentRegexp() {
		return ".+?;\\d+?";
	}

	/**
	 * We parse the argument and return the corresponding opening html.
	 */
	@Override
	public String buildStartingHtml(String argument) {
		String username = argument.substring(0, argument.lastIndexOf(';'));
		String messageId = argument.substring(argument.lastIndexOf(';') + 1);
		return "<div class=\"quoteblock\"><div class=\"quote_author\"><a href=\"#message-" + messageId + "\">"
				+ username + " wrote </a></div>";
	}

	/**
	 * We return the ending html.
	 */
	@Override
	public String buildEndingHtml() {
		return "</div>";
	}

	/**
	 * The shortname of the tag is used to both parse the text looking for the tag,
	 * and for selecting the tag in
	 */
	@Override
	public String shortName() {
		return "quote";
	}

}
