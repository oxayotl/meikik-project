# Meikik

Meikik is a Thymeleaf dialect designed to facilitate the inclusion of user-inputted text into your website. 
It provides three new tag attributes, 
* `text:basic` simply ensures that new lines from the input string are still visible in your website, by replacing them with <br> tags
* `text:bbcode` works similarly to `text:basic` but also support translating into html a variety of BBcode tags like `[b]text in bold[/b]` and `[url="https://www.wikipedia.org/"]Link to the best website[/url]`.
* `text:balises` is used in conjunction with `text:bbcode` to overload which BBCode tags will be interprated from the default value provided to the Dialog constructor.

Meikik also comes with a Spring Boot Starter project for easier configuration.
