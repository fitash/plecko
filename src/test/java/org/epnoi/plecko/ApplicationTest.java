package org.epnoi.plecko;


/**
 * Created by fitash on 9/10/16.
 */

import io.restassured.RestAssured;
import org.epnoi.plecko.config.Profiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({Profiles.DATASTORE,Profiles.SEARCH})
@SpringApplicationConfiguration(classes = PleckoApp.class)
@WebIntegrationTest("server.port:0")

/*
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
*/
public class ApplicationTest {

    @Value("${local.server.port}")
    int port;


    @Test
    public void testUser(){

        System.out.println("port-------> "+port);
    System.out.println("------> "+ RestAssured.get("http://localhost:"+new Integer(port)+"/plecko").asString());

}

}
