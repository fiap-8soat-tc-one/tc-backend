package com.fiap.tc.jpa.ddl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Action;
import org.hibernate.tool.schema.TargetType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.File;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class GenerateSchemaTest {
    @Test
    public void generate() {
        final var settings = getSettings();

        MetadataSources metadataSources = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySettings(settings)
                        .build()
        );

        Reflections reflections = new Reflections("com.fiap.tc.adapters.driven.infrastructure.persistence.entities",
                new SubTypesScanner(false));

        Set<String> entityNames = reflections.getAllTypes();
        entityNames.forEach(metadataSources::addAnnotatedClassName);

        SchemaExport schemaExport = new SchemaExport();

        File outputFile = new File("./target/db-schema.sql");

        if (outputFile.exists()) {
            outputFile.delete();
        }

        log.info("Gerating script in file: {}", outputFile.getAbsolutePath());

        schemaExport.setHaltOnError(true);
        schemaExport.setFormat(true);
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile(outputFile.getAbsolutePath());

        Metadata metadata = metadataSources.buildMetadata();

        schemaExport.execute(EnumSet.of(TargetType.SCRIPT), Action.CREATE, metadata);


        Assertions.assertTrue(outputFile.exists());

        log.info("Finished");
    }

    private Map<String, String> getSettings() {
        Map<String, String> settings = new HashMap<>();
        settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        settings.put("hibernate.hbm2ddl.auto", "create");
        settings.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        return settings;
    }
}
