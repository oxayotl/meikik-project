# Meikik

Meikik is a Thymeleaf dialect designed to facilitate the inclusion of user-inputted text into your website. 

# Usage

Meikik provides three new tag attributes, 
* `text:basic` simply ensures that new lines from the input string are still visible in your website, by replacing them with <br> tags
* `text:bbcode` works similarly to `text:basic` but also support translating into html a variety of BBcode tags like `[b]text in bold[/b]` and `[url="https://www.wikipedia.org/"]Link to the best website[/url]`.
* `text:balises` is used in conjunction with `text:bbcode` to overload which BBCode tags will be interprated from the default value provided to the Dialog constructor.

Meikik also comes with a Spring Boot Starter project for easier configuration.

# Available BBCode tags

Currently available tags :
* `b` : `[b]text[/b]` to write text in bold
* `i` : `[i]text[/i]` to write text in italics
* `u` : `[u]text[/u]` to write text underlined
* `s` : `[s]text[/s]` to write striked through text
* `code` : `[code]text[/code]` to write text in monospace font
* `img` : `[img]https://example.com/picture.png[/img]` to make an url pointing toward an image file into an html &lt;img&gt; element
* `url` : `[url]https://example-url.com[/url]` to make an url into a clickable link, and `[url="https://example-url.com"]text[/url]` to turn text into a clickable url pointing toward example-url.com
* `all` : a shortcut to allow all the above BBCode tags

# Add a custom BBCode tag

Meikik comes with some common BBCode tag directly implemented, but it also allows you to easily implement some custom tags. This can be useful not only for implementing bespoke BBCode tags, but also to provide a custom implementation for some BBCode tags that are heavily dependent on the context of your own project. For instance, if you want to create your own message board and to implement the `[quote]`, the exact generated html is going to be highly dependent on what kind of html the css expects for the quote block, and possibly how your message board will handle creating a link toward a specific message.
Here is a commented example for such an implementation :
```

import io.github.oxayotl.meikik.tag.BBCodeTag;

public class Quote extends BBCodeTag {

	/**
	 * We check check for an opening tag [quote="Username;messageId"]. Since the
	 * regexp will be matched against already html-encoded text, we replace the " by
	 * &quot;
	 */
	@Override
	public String findOpeningRegEx() {
		return "\\[quote=&quot;(.+?;\\d+?)&quot;]";
	}

	/**
	 * We parse the argument and create the corresponding html.
	 */
	@Override
	public String buildStartingHtml(String argument) {
		String username = argument.substring(0, argument.lastIndexOf(';'));
		String messageId = argument.substring(argument.lastIndexOf(';') + 1);
		return "<div class=\"quoteblock\"><div class=\"quote_author\"><a href=\"/messageById/" + messageId + "\">"
				+ username + " wrote </a></div>";
	}

	@Override
	public String findClosingTag() {
		return "[/quote]";
	}

	@Override
	public String buildEndingHtml() {
		return "</div>";
	}

	/**
	 * The shortname of the tag is there to denote if the tag is allowed to be used
	 * in any specific field
	 */
	@Override
	public String shortName() {
		return "quote";
	}

	/**
	 * This tag can contain other tags
	 */
	@Override
	public boolean selfContained() {
		return false;
	}

}
```
