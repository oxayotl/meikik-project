package io.github.oxayotl.meikik.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import io.github.oxayotl.meikik.tag.BBCodeTag;

/**
 * Utility class to load {@link io.github.oxayotl.meikik.tag.BBCodeTag
 * BBCodeTag}
 * 
 * @author Jean-Alexandre Anglès d'Auriac
 *
 */
public class BBCodeTagScanner {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BBCodeTagScanner.class);

	private static String findShortName(Class<?> clazz) {
		try {
			return (String) clazz.getMethod("shortName").invoke(clazz.getConstructor().newInstance());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | InstantiationException e) {
			log.warn("Unable to instantiate BBcode class " + clazz.getCanonicalName(), e);
			return null;
		}
	}

	/**
	 * @param value a string containing comma-separated BBCode shortnames
	 * @return the corresponding {@link io.github.oxayotl.meikik.tag.BBCodeTag
	 *         BBCodeTag}
	 */
	public static List<BBCodeTag> findTagsFromString(String value) {
		List<BBCodeTag> tags = new ArrayList<>();
		Collection<URL> allPackagePrefixes = Arrays.stream(Package.getPackages()).map(p -> p.getName())
				.map(s -> s.split("\\.")[0]).distinct().map(s -> ClasspathHelper.forPackage(s)).reduce((c1, c2) -> {
					Collection<URL> c3 = new HashSet<>();
					c3.addAll(c1);
					c3.addAll(c2);
					return c3;
				}).get();
		ConfigurationBuilder config = new ConfigurationBuilder().addUrls(allPackagePrefixes)
				.addScanners(Scanners.SubTypes);
		Reflections reflections = new Reflections(config);

		// Reflections reflections = new
		// Reflections(ClasspathHelper.forPackage("io.github.oxayotl.meikik"),
//				Scanners.SubTypes);
		Set<Class<? extends BBCodeTag>> classes = reflections.getSubTypesOf(BBCodeTag.class);
		classes.removeIf(clazz -> Modifier.isAbstract(clazz.getModifiers()));

		if ("all".equals(value)) {
			List<BBCodeTag> bbcodes = classes.stream().map(clazz -> {
				try {
					return (BBCodeTag) clazz.getConstructor().newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					log.warn("Unable to instantiate BBcode class " + clazz.getCanonicalName(), e);
					return null;
				}
			}).filter(a -> a != null).collect(Collectors.toList());
			return bbcodes;
		}
		for (String shortname : value.split(",")) {
			List<BBCodeTag> bbcodes = classes.stream().filter(clazz -> shortname.equals(findShortName(clazz)))
					.map(clazz -> {
						try {
							return (BBCodeTag) clazz.getConstructor().newInstance();
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							log.warn("Unable to instantiate BBcode class " + shortname, e);
							return null;
						}
					}).filter(a -> a != null).collect(Collectors.toList());
			if (bbcodes.isEmpty()) {
				log.warn("Unsupported BBCode shortname: " + shortname);
			} else {
				tags.addAll(bbcodes);
			}

		}
		return tags;
	}
}
