package org.epnoi.plecko.storage;

import org.epnoi.plecko.domain.User;
import org.epnoi.plecko.domain.exceptions.UserNotFoundException;
import org.epnoi.plecko.domain.modules.UsersRepository;
import org.epnoi.plecko.storage.graph.UsersGraphRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by fitash on 25/08/16.
 */
public class UsersRepositoryImpl implements UsersRepository{
    @Autowired
    private UsersGraphRepository usersGraphRepository;

    @Override
    public User getUser(String email) throws UserNotFoundException {
        return usersGraphRepository.getUser(email);
    }

    @Override
    public Iterable<User> getUsers() {
        return  usersGraphRepository.getUsers();
    }
}
