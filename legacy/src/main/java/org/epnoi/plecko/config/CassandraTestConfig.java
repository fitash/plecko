package org.epnoi.plecko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * Created by fitash on 24/09/16.
 */
@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
@EnableCassandraRepositories(basePackages = "org.epnoi.plecko.infrastructure.persistence")
@Profile(Profiles.DATASTORE_TEST)
public class CassandraTestConfig extends AbstractCassandraConfiguration {



    public static final String DATA_TABLE_NAME = "user";

    @Autowired
    private Environment environment;

    @Override
    protected String getKeyspaceName() {
        return environment.getProperty("cassandra.keyspace");
    }

    @Override
    @Bean
    public CassandraClusterFactoryBean cluster() {
        final CassandraClusterFactoryBean clusterFactory = new CassandraClusterFactoryBean();
        clusterFactory.setContactPoints(environment.getProperty("cassandra.contactpoints"));
        clusterFactory.setPort(Integer.parseInt(environment.getProperty("cassandra.test.port")));
        return clusterFactory;

    }

    @Override
    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }

}