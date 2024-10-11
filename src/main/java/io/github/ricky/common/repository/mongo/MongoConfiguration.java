package io.github.ricky.common.repository.mongo;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/10
 * @className MongoConfiguration
 * @desc MongoDB配置类
 */
@Configuration
public class MongoConfiguration {

    @Bean
    public MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer() {
        return builder -> builder.applyToConnectionPoolSettings(poolBuilder -> poolBuilder.maxSize(500).minSize(5));
    }

    // TODO 是否需要？？？
    @Bean(name = "mybatisTransactionManager")
    @Primary // 事务默认使用mysql数据库
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // TODO 是否需要？？？
    @Bean(name = "mongoTransactionManager")
    public MongoTransactionManager transactionManager(MongoDatabaseFactory factory){
        return new MongoTransactionManager(factory);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory, MongoConverter mongoConverter) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory, mongoConverter);
        mongoTemplate.setWriteConcern(WriteConcern.MAJORITY);
        mongoTemplate.setWriteConcernResolver(action -> WriteConcern.MAJORITY);
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        mongoTemplate.setReadPreference(ReadPreference.secondaryPreferred());
        return mongoTemplate;
    }

}
