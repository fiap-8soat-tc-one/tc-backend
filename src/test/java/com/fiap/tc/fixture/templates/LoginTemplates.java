package com.fiap.tc.fixture.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fiap.tc.adapter.repository.entity.security.UserEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class LoginTemplates implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(UserEntity.class).addTemplate("valid", new Rule() {
            {
                add("id", random(Long.class, range(1, 100)));
                add("uuid", UUID.randomUUID());
                add("login", random("myller@teste.com"));
                add("nome", random("Myller"));
                add("email", random("myller@teste.com"));
                add("senha", RandomStringUtils.randomAlphabetic(32));
                add("senhaAes", RandomStringUtils.randomAlphabetic(32));


            }
        });

    }

}
