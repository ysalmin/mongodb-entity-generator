package com.salm;

import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.salm.MongoDataImport;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

@Configuration
@EnableMongoRepositories
@ComponentScan(basePackageClasses = {MongoDataImport.class})
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "demo12";
    }

    @Override
    public Mongo mongo() throws Exception {

        return new Mongo(new ArrayList<ServerAddress>() {{
            add(new ServerAddress("127.0.0.1", 27017));
        }});
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.salm.domain";
    }
}
