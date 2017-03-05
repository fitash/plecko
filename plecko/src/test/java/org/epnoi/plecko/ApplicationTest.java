package org.epnoi.plecko;


/**
 * Created by fitash on 9/10/16.
 */

import com.jayway.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ApplicationTest {

   @LocalServerPort
    int port;


    @Test
    public void testUser(){
        String whatever = "";

        System.out.println("port-------> "+port);
    System.out.println("------> "+ RestAssured.get("http://localhost:"+new Integer(port)+"/plecko/user").asString());



        when().get("http://localhost:"+new Integer(port)+"/plecko/user")
                .then().assertThat().body("email", equalTo("test@plecko.org"));

}

    @Test
    public void testNonExistantUser(){
        String whatever = "";

        System.out.println("port-------> "+port);
        System.out.println("------> "+ RestAssured.get("http://localhost:"+new Integer(port)+"/plecko/user").asString());



        when().get("http://localhost:"+new Integer(port)+"/plecko/user")
                .then().assertThat().body("email", equalTo("test@plecko.org"));

    }


}
