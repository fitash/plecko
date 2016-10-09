package org.epnoi.plecko.infrastructure.storage.datastore;

import org.epnoi.plecko.domain.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

/**
 * Created by fitash on 24/09/16.
 */
public interface UsersDatastoreRepository extends CassandraRepository<User>{

    @Query("select * from user where email = ?0 ")
    Iterable<User> getUser(String email);

}
