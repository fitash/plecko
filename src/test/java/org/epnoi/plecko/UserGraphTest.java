package org.epnoi.plecko;

import org.epnoi.plecko.config.Profiles;
import org.epnoi.plecko.domain.User;
import org.epnoi.plecko.storage.graph.UsersGraphRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PleckoApp.class)
@ActiveProfiles(Profiles.GRAPH)
public class UserGraphTest {

    public static final String TEST_USER_NAME = "test";
    public static final String TEST_USER_EMAIL = "test@plecko.org";

    User testUser;

    @Autowired
    private UsersGraphRepository repository;

    @Before
    public void init(){

        repository.deleteAll();
        this.testUser = new User(TEST_USER_NAME, TEST_USER_EMAIL);

    }

    @Test
    public void testGraph() {
        System.out.println("----------> "+repository.getUsers());
       repository.save(testUser);
        System.out.println("----------> "+repository.getUsers());
        User retrievedUser =repository.getUser(TEST_USER_EMAIL);
        System.out.println("This is the retrieved user "+retrievedUser);
       // assertThat(retrievedUser, hasProperty("mail", equalTo(TEST_USER_EMAIL)));
    }


}