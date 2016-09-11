package org.epnoi.plecko.domain.modules;

import org.epnoi.plecko.domain.User;
import org.epnoi.plecko.domain.exceptions.UserNotFoundException;

/**
 * Created by fitash on 25/08/16.
 */
public interface UsersRepository {
    User getUser(String email) throws UserNotFoundException;
    Iterable<User> getUsers();
}
