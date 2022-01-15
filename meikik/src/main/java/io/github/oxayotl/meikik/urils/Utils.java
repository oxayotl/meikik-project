package io.github.oxayotl.meikik.urils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.oxayotl.meikik.tag.BBCodeTag;

public class Utils {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Utils.class);

	public static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		return reader.lines().filter(line -> line.endsWith(".class")).map(line -> getClass(line, packageName))
				.collect(Collectors.toSet());
	}

	private static Class<?> getClass(String className, String packageName) {
		try {
			return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
		} catch (ClassNotFoundException e) {
			// handle the exception
		}
		return null;
	}

	private static String findShortName(Class<?> clazz) {
		try {
			return (String) clazz.getMethod("shortName").invoke(clazz.getConstructor().newInstance());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | InstantiationException e) {
			log.warn("Unable to instantiate BBcode class " + clazz.getCanonicalName(), e);
			return null;
		}
	}

	public static List<BBCodeTag> parseTagString(String value) {
		List<BBCodeTag> tags = new ArrayList<>();
		Set<Class<?>> classes = findAllClassesUsingClassLoader("io.github.oxayotl.meikik.tag.impl");
		for (String shortname : value.split(",")) {
			Optional<BBCodeTag> bbcode = classes.stream().filter(clazz -> shortname.equals(findShortName(clazz)))
					.findFirst().map(clazz -> {
						try {
							return (BBCodeTag) clazz.getConstructor().newInstance();
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							log.warn("Unable to instantiate BBcode class " + shortname, e);
							return null;
						}
					});
			bbcode.ifPresentOrElse(tags::add, () -> log.warn("Unspported BBCode shortname: " + shortname));

		}
		return tags;
	}
}
