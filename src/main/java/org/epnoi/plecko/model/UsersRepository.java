package org.epnoi.plecko.model;

import org.epnoi.plecko.model.exceptions.UserNotFoundException;

/**
 * Created by fitash on 25/08/16.
 */
public interface UsersRepository {
    User getUser(String email) throws UserNotFoundException;
    Iterable<User> getUsers();
}
