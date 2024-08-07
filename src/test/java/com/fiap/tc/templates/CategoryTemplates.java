package com.fiap.tc.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fiap.tc.infrastructure.adapter.persistence.entity.CategoryEntity;
import com.fiap.tc.infrastructure.adapter.persistence.entity.embeddable.Audit;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.infrastructure.adapter.web.requests.CategoryRequest;

import java.util.UUID;

public class CategoryTemplates implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(CategoryEntity.class).addTemplate("valid", new Rule() {
            {
                add("id", random(Integer.class, range(1, 100)));
                add("uuid", UUID.randomUUID());
                add("name", random("Sandwich", "Sides", "Drink", "Dessert"));
                add("description", random("Sandwich", "Sides", "Drink", "Dessert"));
                add("audit", one(Audit.class, "valid"));
            }
        });

        Fixture.of(Category.class).addTemplate("valid", new Rule() {
            {
                add("id", UUID.randomUUID());
                add("name", random("Sandwich", "Sides", "Drink", "Dessert"));
                add("description", random("Sandwich", "Sides", "Drink", "Dessert"));
                add("active", true);
            }
        });

        Fixture.of(CategoryRequest.class).addTemplate("valid", new Rule() {
            {
                add("name", random("Sandwich", "Sides", "Drink", "Dessert"));
                add("description", random("Sandwich", "Sides", "Drink", "Dessert"));
                add("active", true);
            }
        });

    }

}
