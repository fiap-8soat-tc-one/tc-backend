package com.fiap.tc.fixture;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;

public class FixtureTest {

    public static final String TEMPLATES_PATH = "com.fiap.tc.fixture.templates";

    public FixtureTest() {
        loadTemplates(TEMPLATES_PATH);
    }
}
