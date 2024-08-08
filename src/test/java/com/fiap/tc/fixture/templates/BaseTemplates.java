package com.fiap.tc.fixture.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.embeddable.Audit;

import java.time.LocalDateTime;

public class BaseTemplates implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(Audit.class).addTemplate("valid", new Rule() {
            {
                add("registerDate", LocalDateTime.now());
                add("active", true);
            }
        });

    }

}
