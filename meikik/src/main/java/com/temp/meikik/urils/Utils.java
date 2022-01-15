package com.temp.meikik.urils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.temp.meikik.domain.Tag;
import com.temp.meikik.tag.BBCodeTag;
import com.temp.meikik.tag.impl.Bold;
import com.temp.meikik.tag.impl.Italic;
import com.temp.meikik.tag.impl.Url;

public class Utils {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Utils.class);

	private static BBCodeTag findBbCodeTag(Tag tag) {
		switch (tag) {
		case B:
			return new Bold();
		case I:
			return new Italic();
		case URL:
			return new Url();
		default:
			throw new RuntimeException("Unknown tag: " + tag);
		}
	}

	public static Set<Class> findAllClassesUsingClassLoader(String packageName) {
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		return reader.lines().filter(line -> line.endsWith(".class")).map(line -> getClass(line, packageName))
				.collect(Collectors.toSet());
	}

	private static Class getClass(String className, String packageName) {
		try {
			return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
		} catch (ClassNotFoundException e) {
			// handle the exception
		}
		return null;
	}

	private static String findShortName(Class clazz) {
		try {
			return (String) clazz.getMethod("shortName", null).invoke(clazz.getConstructor().newInstance(), null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<BBCodeTag> parseTagString(String value) {
		List<BBCodeTag> tags = new ArrayList<>();
		Set<Class> classes = findAllClassesUsingClassLoader("com.temp.meikik.tag.impl");
		for (String shortname : value.split(",")) {
			Optional<BBCodeTag> bbcode = classes.stream().filter(clazz -> shortname.equals(findShortName(clazz))).findFirst()
					.map(clazz -> {
						try {
							return (BBCodeTag) clazz.getConstructor().newInstance();
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							// TODO Auto-generated catch block
							log.warn("Unable to instantiate BBcode class " + shortname, e);
							return null;
						}
					});
			bbcode.ifPresentOrElse(tags::add, () -> log.warn("Unspported BBCode shortname: " + shortname));

		}
		return tags;
	}
}
