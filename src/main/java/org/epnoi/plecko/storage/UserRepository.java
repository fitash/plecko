package org.epnoi.plecko.storage;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.epnoi.plecko.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by fitash on 26/09/16.
 */
@Component
public class UserRepository {

    @Autowired
    CassandraOperations cassandraTemplate;

    @PostConstruct
    public void init(){

        System.out.println(">>"+cassandraTemplate.select(QueryBuilder.select().all().from("plecko","user"), User.class));

    }

}
