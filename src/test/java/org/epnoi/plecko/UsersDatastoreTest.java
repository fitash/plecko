package org.epnoi.plecko;

import org.epnoi.plecko.config.Profiles;
import org.epnoi.plecko.domain.User;
import org.epnoi.plecko.storage.datastore.UsersDatastoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PleckoApp.class)
@ActiveProfiles(Profiles.DATASTORE)
public class UsersDatastoreTest {

    public static final String TEST_USER_NAME = "test";
    public static final String TEST_USER_EMAIL = "test@plecko.org";

    User testUser;

    @Autowired
    private UsersDatastoreRepository repository;

    @Before
    public void init(){
    testUser = new User(TestConstants.TEST_USER_EMAIL,TestConstants.TEST_USER_NAME);
        repository.deleteAll();

    }

    @Test
    public void unknownUserTest() {
              repository.save(testUser);
        MapId id = BasicMapId.id("email", TestConstants.TEST_USER_EMAIL+"whatever");
       User retrievedUser = repository.findOne(id);
       assertThat(retrievedUser, is(nullValue()));


    }

    @Test
    public void retrieveUserTest() {
        repository.save(testUser);
        MapId id = BasicMapId.id("email", TestConstants.TEST_USER_EMAIL);
        User retrievedUser = repository.findOne(id);
        assertThat(retrievedUser, is(equalTo(testUser)));


    }


}