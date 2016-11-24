package edu.aabramov.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import javax.annotation.Resource;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {
    
    @Resource
    private Environment environment;
    
    @Override
    protected String getDatabaseName() {
        return environment.getProperty("mongo.db.name");
    }
    
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }
    
}
