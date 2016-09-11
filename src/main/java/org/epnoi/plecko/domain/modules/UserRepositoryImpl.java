package org.epnoi.plecko.domain.modules;

import org.epnoi.plecko.domain.User;
import org.epnoi.plecko.domain.exceptions.UserNotFoundException;
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
