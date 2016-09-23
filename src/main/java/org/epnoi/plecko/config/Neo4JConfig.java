package org.epnoi.plecko.config;


import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@Profile(Profiles.GRAPH)
@EnableNeo4jRepositories(basePackages = "org.epnoi.plecko.storage.graph")
@EnableTransactionManagement
public class Neo4JConfig extends Neo4jConfiguration{
    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("org.epnoi.plecko.domain");
    }


}

