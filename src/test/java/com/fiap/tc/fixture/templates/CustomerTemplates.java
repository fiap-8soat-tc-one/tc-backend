package com.fiap.tc.fixture.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.CustomerEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.embeddable.Audit;
import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.core.domain.requests.CustomerRequest;

import java.util.UUID;

public class CustomerTemplates implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(CustomerEntity.class).addTemplate("valid", new Rule() {
            {
                add("id", random(Integer.class, range(1, 100)));
                add("uuid", UUID.randomUUID());
                add("name", random("Myller", "Jean", "Caio"));
                add("document", random("52735617017", "03014336076", "90819176095"));
                add("email", random("myllersakaguchi@gmail.com", "test@test.com"));
                add("audit", one(Audit.class, "valid"));
            }
        });

        Fixture.of(Customer.class).addTemplate("valid", new Rule() {
            {
                add("id", UUID.randomUUID());
                add("name", random("Myller", "Jean", "Caio"));
                add("document", random("52735617017", "03014336076", "90819176095"));
                add("email", random("myllersakaguchi@gmail.com", "test@test.com"));
            }
        });

        Fixture.of(CustomerRequest.class).addTemplate("valid", new Rule() {
            {
                add("name", random("Myller", "Jean", "Caio"));
                add("document", random("52735617017", "03014336076", "90819176095"));
                add("email", random("myllersakaguchi@gmail.com", "test@test.com"));
            }
        });

    }

}
