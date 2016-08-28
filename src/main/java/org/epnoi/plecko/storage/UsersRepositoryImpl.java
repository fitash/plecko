package org.epnoi.plecko.storage;

import org.epnoi.plecko.model.User;
import org.epnoi.plecko.model.UsersRepository;
import org.epnoi.plecko.model.exceptions.UserNotFoundException;

/**
 * Created by fitash on 25/08/16.
 */
public class UsersRepositoryImpl implements UsersRepository{

    @Override
    public User getUser(String email) throws UserNotFoundException {
        return null;
    }

    @Override
    public Iterable<User> getUsers() {
        return null;
    }
}
