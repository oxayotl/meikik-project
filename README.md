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
