package org.epnoi.plecko.infrastructure.persistence;


import org.epnoi.plecko.PleckoApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PleckoApp.class)
public class UserRepositoryTest {

    @Test
    public void testGraph() {

    }

}