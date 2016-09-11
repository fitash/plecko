package org.epnoi.knowledgebase;

import org.epnoi.plecko.PleckoApp;
import org.epnoi.plecko.domain.User;
import org.epnoi.plecko.storage.UsersGraphRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PleckoApp.class)
public class UserGraphTest {

    public static final String TEST_USER_NAME = "test";
    public static final String TEST_USER_EMAIL = "test@plecko.org";

    User testUser;

    @Autowired
    private UsersGraphRepository repository;

    @Before
    public void init(){
        repository.deleteAll();
        User testUser = new User(TEST_USER_NAME, TEST_USER_EMAIL);

    }

    @Test
    public void testGraph() {
       repository.save(testUser);
        User retrievedUser =repository.getUser(TEST_USER_EMAIL);
        assertThat(retrievedUser, hasProperty("mail", equalTo(TEST_USER_EMAIL)));
    }


}