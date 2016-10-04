package org.epnoi.plecko.storage;

import org.epnoi.plecko.domain.User;
import org.epnoi.plecko.domain.exceptions.UserNotFoundException;
import org.epnoi.plecko.domain.modules.repositories.UsersRepository;
import org.epnoi.plecko.storage.datastore.UsersDatastoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by rgonza on 29/8/16.
 */
@Component
public class UserRepositoryImpl implements UsersRepository {


    @Autowired
    private UsersDatastoreRepository repository;

    @Override
    public User getUser(String email) throws UserNotFoundException {

        Iterator<User> iterator = repository.getUser(email).iterator();
        if (iterator.hasNext()){
            return iterator.next();
        }else{
            throw new UserNotFoundException();
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
