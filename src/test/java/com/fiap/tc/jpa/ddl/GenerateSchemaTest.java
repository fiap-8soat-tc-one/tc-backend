package com.fiap.tc.jpa.ddl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Action;
import org.hibernate.tool.schema.TargetType;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.File;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@Slf4j
public class GenerateSchemaTest {
    @Test
    public void generate() {
        Map<String, String> settings = new HashMap<>();

        /*
         * settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
         * settings.put("hibernate.hbm2ddl.auto", "create");
         * settings.put("hibernate.dialect.storage_engine", "innodb");
         * settings.put("hibernate.physical_naming_strategy",
         * "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
         */

        settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        settings.put("hibernate.hbm2ddl.auto", "create");
        settings.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");


        MetadataSources metadataSources = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySettings(settings)
                        .build()
        );

        Reflections reflections = new Reflections("com.fiap.tc.adapter.repository.entity", new SubTypesScanner(false));

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


        assertTrue(outputFile.exists());

        log.info("Finished");
    }
}
