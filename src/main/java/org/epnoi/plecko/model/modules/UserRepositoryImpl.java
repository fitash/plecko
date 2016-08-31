package org.epnoi.plecko.model.modules;

import org.epnoi.plecko.model.User;
import org.epnoi.plecko.model.exceptions.UserNotFoundException;
import org.epnoi.plecko.storage.UsersRepositoryImpl;
import org.springframework.stereotype.Component;

/**
 * Created by rgonza on 29/8/16.
 */
@Component
public class UserRepositoryImpl implements UsersRepository{
    @Override
    public User getUser(String email) throws UserNotFoundException {
        return null;
    }

    @Override
    public Iterable<User> getUsers() {
        return null;
    }
}
