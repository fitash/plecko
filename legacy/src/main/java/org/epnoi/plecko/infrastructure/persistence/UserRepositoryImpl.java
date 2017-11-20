package org.epnoi.plecko.infrastructure.persistence;

import org.epnoi.plecko.domain.model.User;
import org.epnoi.plecko.domain.exceptions.UserNotFoundException;
import org.epnoi.plecko.domain.ports.secundary.UsersRepository;
import org.epnoi.plecko.infrastructure.persistence.datastore.UsersDatastoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.stereotype.Component;

/**
 * Created by rgonza on 29/8/16.
 */
@Component
public class UserRepositoryImpl implements UsersRepository {


    @Autowired
    private UsersDatastoreRepository repository;

    @Override
    public User getUser(String email) throws UserNotFoundException {
        MapId id = BasicMapId.id("email", email);
        User retrievedUser = repository.findOne(id);
        if (retrievedUser!=null){
            return retrievedUser;
        }else{
            throw new UserNotFoundException("User "+ email + " could not be found");
        }
    }

    @Override
    public Iterable<User> getUsers() {
        return null;
    }

    @Override
    public void removeAll() {

    }
}
