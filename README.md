# Meikik

Meikik is a Thymeleaf dialect designed to facilitate the inclusion of BBCode-formated text into your website. 

# Usage

Meikik provides two new tag attributes, 
* `text:bbcode` translate into HTML code a variety of BBcode tags like `[b]text in bold[/b]` and `[url="https://www.wikipedia.org/"]Link to the best website[/url]`.
* `text:ubbcode` works as `text:bbcode` but doesn't escape any xml before translating BBcode tags into html.
* `text:allowed-tags` is used in conjunction with `text:bbcode` to specify which tag categories should be translated into HTML code. If this tag is not present, a configurable default value will be used instead.

Usage example:

```
<div text:bbcode="${someBBcodeText}" text:allowed-tags="b,url">
	BBCode text
</div>
```

# Available BBCode tags

Implemented tags :
* `b` : `[b]text[/b]` to write text in bold
* `i` : `[i]text[/i]` to write text in italics
* `u` : `[u]text[/u]` to write text underlined
* `s` : `[s]text[/s]` to write striked through text
* `code` : `[code]text[/code]` to write text in monospace font
* `img` : `[img]https://example.com/picture.png[/img]` to make an url pointing toward an image file into an html &lt;img&gt; element
* `url` : `[url]https://example-url.com[/url]` to make an url into a clickable link, and `[url="https://example-url.com"]text[/url]` to turn text into a clickable url pointing toward example-url.com
* `all` : a shortcut to allow all the above BBCode tags, plus any custom tag defined in the project.

# Adding Meikik to your project
 
## Add Meikik using a Spring boot starter project

If your project use spring, you can simply add the Meikik starter project as a dependency.

```
<dependency>
    <groupId>io.github.oxayotl</groupId>
    <artifactId>spring-boot-starter-meikik</artifactId>
    <version>1.1.1</version>
</dependency>
```

You can now directly use Meikik in your template files. You can add the property `meikik.default-allowed-tags` to select which tags are allowed when `text:allowed-tags` is not set, using the same syntax as `text:allowed-tags` .


## Add Meikik without Spring

Start by adding the Meikik dependency to your project.
```
<dependency>
    <groupId>io.github.oxayotl</groupId>
    <artifactId>meikik</artifactId>
    <version>1.1.1</version>
</dependency>
```
You can then add the dialect to your `TemplateEngine`.

# Add a custom BBCode tag

Meikik comes with some common BBCode tag directly implemented, but it also allows you to easily implement some custom tags. This can be useful not only for implementing bespoke BBCode tags, but also to provide a custom implementation for some BBCode tags that are heavily dependent on the context of your own project. For instance, if you want to create your own message board and to implement the `[quote]`, the exact generated HTML is going to be highly dependent on what kind of HTML the CSS expects for the quote block, and possibly how your message board handle creating a link toward a specific message.

To create a custom BBCode tag, you must create a class that extends the abstract class either `BBCodeTagContainer` (if your tag can contain other tags, like `[i]` for instance) or `BBCodeTagFinal` (if your tag cannot contain other BBCode tags, like `[img]` for instance).

Here is an example for such an implementation :

```
import io.github.oxayotl.meikik.tag.BBCodeTagContainer;

/**
 * We are extending BBCodeTagContainer rather than BBCodeTagFinal because the
 * [quote] tag can include other BBCode tags, for instance [quote="Meikik;1"]I'm
 * [b]yelling[/b][/quote]
 */
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
		return "<div class=\"quoteblock\"><div class=\"quote_author\"><a href=\"/messageById/" + messageId + "\">"
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
```

If you are using the Spring boot starter project, annotate your custom BBCode classes with `@Component` to make them available in thymeleaf. If you do not use the starter project, then you should provide one instance of each custom BBCodeTag class in the second parameter of MeikikDialect.

# Example project

This repository also include an example project of how to use Meikik.
