package com.fiap.tc.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fiap.tc.infrastructure.adapter.persistence.entity.CategoryEntity;
import com.fiap.tc.infrastructure.adapter.persistence.entity.ProductEntity;
import com.fiap.tc.infrastructure.adapter.persistence.entity.embeddable.Audit;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductTemplates implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(ProductEntity.class).addTemplate("valid", new Rule() {
            {
                add("id", random(Integer.class, range(1, 100)));
                add("uuid", UUID.randomUUID());
                add("name", random("hamburger", "hot dog", "fries"));
                add("description", random("hamburger", "hot dog", "fries"));
                add("price", random(BigDecimal.valueOf(20), BigDecimal.valueOf(30), BigDecimal.valueOf(40)));
                add("category", one(CategoryEntity.class, "valid"));
                add("audit", one(Audit.class, "valid"));

            }
        });
    }

}
