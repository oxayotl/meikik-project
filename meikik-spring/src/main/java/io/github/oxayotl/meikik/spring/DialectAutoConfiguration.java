package io.github.oxayotl.meikik.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;

import io.github.oxayotl.meikik.dialect.MeikikDialect;
import io.github.oxayotl.meikik.tag.BBCodeTag;
import io.github.oxayotl.meikik.tag.impl.Bold;
import io.github.oxayotl.meikik.tag.impl.Code;
import io.github.oxayotl.meikik.tag.impl.Img;
import io.github.oxayotl.meikik.tag.impl.Italic;
import io.github.oxayotl.meikik.tag.impl.StrikeThrough;
import io.github.oxayotl.meikik.tag.impl.Underline;
import io.github.oxayotl.meikik.tag.impl.Url;
import io.github.oxayotl.meikik.tag.impl.UrlWithArgument;

@Configuration
/**
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class DialectAutoConfiguration {
	@Value("${meikik.default-allowed-tags:b,i,u}")
	private String defaultTags;

//	@Autowired
	private final List<BBCodeTag> tags;

	@Bean
	public IDialect textDialect() {
		List<BBCodeTag> allTags = new ArrayList<>(Arrays.asList(new Bold(), new Code(), new Img(), new Italic(),
				new StrikeThrough(), new Underline(), new Url(), new UrlWithArgument()));
		allTags.addAll(tags);
		return new MeikikDialect(defaultTags, allTags);
	}

	@Autowired
	public DialectAutoConfiguration(List<BBCodeTag> tags) {
		this.tags = tags;
	}
}
