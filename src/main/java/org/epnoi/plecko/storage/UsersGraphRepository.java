package org.epnoi.plecko.storage;

import org.epnoi.plecko.model.Concept;
import org.epnoi.plecko.model.RelatedResult;
import org.epnoi.plecko.model.Relation;
import org.epnoi.plecko.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Created by rgonza on 10/07/16.
 */
@Repository
public interface UsersGraphRepository extends GraphRepository<User> {
   @Query("MATCH (user:User) "+
    "WHERE  user.email={email} "+
    "RETURN user")

   User getUser(@Param("email") String email);


    @Query("MATCH (user:User) "+
            "RETURN user"
    )
    Iterable<User> getUsers();




}
