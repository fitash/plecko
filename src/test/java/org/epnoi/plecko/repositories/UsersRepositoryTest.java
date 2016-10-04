package org.epnoi.plecko.repositories;

import org.epnoi.plecko.PleckoApp;
import org.epnoi.plecko.TestConstants;
import org.epnoi.plecko.config.Profiles;
import org.epnoi.plecko.domain.Item;
import org.epnoi.plecko.domain.User;
import org.epnoi.plecko.domain.exceptions.UserNotFoundException;
import org.epnoi.plecko.domain.modules.repositories.UsersRepository;
import org.epnoi.plecko.storage.search.ItemsSearchRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by fitash on 30/09/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PleckoApp.class)
@ActiveProfiles({Profiles.SEARCH, Profiles.DATASTORE})

public class UsersRepositoryTest {


    @Autowired
    private UsersRepository usersRepository;


    final  User testUser = new User(TestConstants.TEST_USER_EMAIL, TestConstants.TEST_USER_NAME);


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {


    }

    @Test
    public void correctStorageAndRetrieval() throws UserNotFoundException {

        User retrievedItem = this.usersRepository.getUser(TestConstants.TEST_USER_EMAIL);
        System.out.println("------------> " + retrievedItem);
        assertThat(retrievedItem, is(equalTo(testUser)));

    }
}