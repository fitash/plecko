package org.epnoi.plecko.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
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
@Profile(Profiles.DATASTORE)
public class CassandraConfig extends AbstractCassandraConfiguration {



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
        clusterFactory.setPort(Integer.parseInt(environment.getProperty("cassandra.port")));
/*
try {
    final Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9042).build();

    final Session session = cluster.connect();
    session.execute(KEYSPACE_CREATION_QUERY);
    session.execute(KEYSPACE_ACTIVATE_QUERY);
}catch(Exception e){e.printStackTrace();}
        System.out.println("Cluster created with contact points [" + environment.getProperty("cassandra.contactpoints") + "] " + "& port [" + Integer.parseInt(environment.getProperty("cassandra.port")) + "].");
  */
        return clusterFactory;

    }
    /*

    @Override
    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
    */
}