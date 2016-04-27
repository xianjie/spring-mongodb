package com.mongodb.config;

/**
 * 可以使用当前这种无配置方法
 * 也可以在spring 配置文件里面配置
 */


/*import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;


@Configuration
public class SpringMongoConfig {

    public MongoDbFactory mongoDbFactory() throws Exception {
        ServerAddress serverAddress = new ServerAddress("192.168.30.172");

        MongoClient mongo = new MongoClient(serverAddress);

        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongo, "exercise");
        return simpleMongoDbFactory;

    }

    public
    @Bean
    MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

        // show error, should off on production to speed up performance
        mongoTemplate.setWriteConcern(WriteConcern.SAFE);

        return mongoTemplate;

    }

}*/
