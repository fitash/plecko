package org.epnoi.plecko.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

/**
 * Created by fitash on 25/08/16.
 */
@Component
public class DeliveryHandler {
    @Autowired
    private UsersRepository usersRepository;

    public void deliver()  {
        for (User user : usersRepository.getUsers()) {
            deliver(user);
        }

    }

    private void deliver(User user) {
        System.out.println("Delivering content to user " + user.getEmail());
    }
}
