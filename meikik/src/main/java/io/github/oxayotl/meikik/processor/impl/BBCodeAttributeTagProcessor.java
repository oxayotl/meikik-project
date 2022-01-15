package io.github.oxayotl.meikik.processor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thymeleaf.expression.Strings;

import io.github.oxayotl.meikik.domain.TagReplacement;
import io.github.oxayotl.meikik.processor.AbstractTextAttributeTagProcessor;
import io.github.oxayotl.meikik.service.TagService;
import io.github.oxayotl.meikik.tag.BBCodeTag;
import io.github.oxayotl.meikik.urils.Utils;

public class BBCodeAttributeTagProcessor extends AbstractTextAttributeTagProcessor {

	private List<BBCodeTag> defaultTags;
	private static final String ATTR_NAME = "bbcode";

	public BBCodeAttributeTagProcessor(final String dialectPrefix, String tags) {
		super(dialectPrefix, ATTR_NAME);
		defaultTags = Utils.parseTagString(tags);
	}

	private BBCodeTag findOpeningTag(String substring, List<BBCodeTag> tags, List<BBCodeTag> currentlyOpenedTags) {
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

	@Override
	protected String processString(String attributeContent, List<BBCodeTag> tags) {
		if (tags == null) {
			tags = defaultTags;
		}
		String original = new Strings(null).escapeXml(attributeContent);
		original = original.replace(System.getProperty("line.separator"), "<br />");

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
						TagReplacement tr = TagService.processEndRegEx(closingTag, original.substring(i));
						i += tr.getReplacedCharacters() - 1;
						lastTagIndex = i + 1;
						int j;
						for (j = currentlyOpenedTags.size() - 1; currentlyOpenedTags.get(j) != closingTag; j--) {
							result.append(currentlyOpenedTags.get(j).buildEndingHtml());
						}
						result.append(currentlyOpenedTags.get(j).buildEndingHtml());
						currentlyOpenedTags.remove(j);
						currentlyOpenedTagsHtml.remove(j);
						for (j = j; j < currentlyOpenedTags.size(); j++) {
							result.append(currentlyOpenedTagsHtml.get(j));
						}
					}

				} else {
					BBCodeTag openedTag = findOpeningTag(original.substring(i), tags, currentlyOpenedTags);
					if (openedTag != null) {
						result.append(original.substring(lastTagIndex, i));
						TagReplacement tr = TagService.processOpeningRegEx(openedTag, original.substring(i));
						i += tr.getReplacedCharacters() - 1;
						lastTagIndex = i + 1;
						result.append(tr.getReplacement());
						currentlyOpenedTags.add(openedTag);
						currentlyOpenedTagsHtml.add(tr.getReplacement());
					}
				}
			}
		}
		result.append(original.substring(lastTagIndex));

//		if (tags.contains(Tag.I)) {
//			original = original.replaceAll("\\[i](.*?)\\[/i]", "<i>$1</i>");
//		}
//		if (tags.contains(Tag.B)) {
//			original = original.replaceAll("\\[b](.*?)\\[/b]", "<b>$1</b>");
//		}
//		if (tags.contains(Tag.URL)) {
//			original = original.replaceAll("\\[url=&quot;(.*?)&quot;](.*?)\\[/url]",
//					"<a target=\"_blank\" href=\"$1\">$2</a>");
//		}
		return result.toString();

	}

}