package org.epnoi.plecko.infrastructure.persistence.datastore;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.epnoi.plecko.PleckoApp;
import org.epnoi.plecko.TestConstants;
import org.epnoi.plecko.config.Profiles;
import org.epnoi.plecko.domain.model.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PleckoApp.class)
@ActiveProfiles({Profiles.SEARCH, Profiles.DATASTORE_TEST})

public class UsersDatastoreTest {
    @Autowired
    CassandraOperations cassandraTemplate;

    public static final String KEYSPACE_CREATION_QUERY = "create keyspace if not exists plecko with replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};";

    public static final String KEYSPACE_ACTIVATE_QUERY = "USE plecko;";

    public static final String USER_TABLE_CREATION_QUERY = "create table if not exists plecko.user(email text, name text, primary key (email));";

    @BeforeClass
    public static void startCassandraEmbedded() throws InterruptedException, TTransportException, ConfigurationException, IOException {


        EmbeddedCassandraServerHelper.startEmbeddedCassandra();

        //EmbeddedCassandraServerHelper.startEmbeddedCassandra(EmbeddedCassandraServerHelper.CASSANDRA_RNDPORT_YML_FILE);
        int port = EmbeddedCassandraServerHelper.getNativeTransportPort();
        System.out.println(port);

        final Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9142).build();
        System.out.println("Server Started at 127.0.0.1:.. ");
        final Session session = cluster.connect();


        session.execute(KEYSPACE_CREATION_QUERY);
        session.execute(KEYSPACE_ACTIVATE_QUERY);
        session.execute(USER_TABLE_CREATION_QUERY);
        System.out.println("KeySpace created and activated.");
        Thread.sleep(5000);
    }

    User testUser = new User(TestConstants.TEST_USER_EMAIL, TestConstants.TEST_USER_NAME);

    @Autowired
    private UsersDatastoreRepository repository;


    @Before
    public void init() {
        testUser = new User(TestConstants.TEST_USER_EMAIL, TestConstants.TEST_USER_NAME);
        repository.deleteAll();

    }


    @Test
    public void unknownUserTest() {
        repository.save(testUser);
        MapId id = BasicMapId.id("email", TestConstants.TEST_USER_EMAIL + "whatever");
        User retrievedUser = repository.findOne(id);
        assertThat(retrievedUser, is(nullValue()));
    }


    @Test
    public void retrieveUserTest() {
        repository.save(testUser);
        MapId id = BasicMapId.id("email", TestConstants.TEST_USER_EMAIL);
        User retrievedUser = repository.findOne(id);
        System.out.println("retrieved user ---> " + retrievedUser);
        assertThat(retrievedUser, is(equalTo(testUser)));


    }

    @AfterClass
    public static void clean() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }


}