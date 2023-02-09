package io.github.oxayotl.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Messages {
	private final long id;
	private final String username;
	private final String text;
}
