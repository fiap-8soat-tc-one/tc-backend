package com.fiap.tc.util;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;

public class BaseTest {

	public static final String TEMPLATES_PATH = "com.fiap.tc.templates";

	public BaseTest() {
		loadTemplates(TEMPLATES_PATH);
	}
}
