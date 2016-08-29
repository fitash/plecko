package org.epnoi.knowledgebase;

import org.epnoi.plecko.model.modules.DeliveryHandler;
import org.epnoi.plecko.model.User;
import org.epnoi.plecko.model.UsersRepository;
import org.epnoi.plecko.model.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.when;

/**
 * Created by fitash on 25/08/16.
 */

public class DeliveryHandlerTest {

    public static final String TEST_USER_NAME = "test";
    public static final String TEST_USER_EMAIL = "test@plecko.org";
    @Mock
    UsersRepository usersRepository;


    @InjectMocks
    DeliveryHandler deliveryHandler;

    @Before
    public void init() throws UserNotFoundException{
        MockitoAnnotations.initMocks(this);
        User testUser = new User(TEST_USER_NAME, TEST_USER_EMAIL);
        when(usersRepository.getUser("test@plecko.org")).thenReturn(testUser);
        when(usersRepository.getUsers()).thenReturn(Arrays.asList(testUser));
    }

    @Test
    public void deliverTest(){
        deliveryHandler.deliver();
    }

    @Test
    public void deliverTest2() throws UserNotFoundException{
        deliveryHandler.deliver();
        System.out.println(usersRepository.getUser("test@plecko.org"));
        System.out.println(usersRepository.getUser("test@plecko.org"));
    }

}
