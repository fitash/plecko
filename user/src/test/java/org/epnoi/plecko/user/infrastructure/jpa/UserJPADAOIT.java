package org.epnoi.plecko.user.infrastructure.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.epnoi.plecko.user.infrastructure.jpa.UserFixtureBuilder.anyUser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by rgonzalez on 07/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserJPADAOIT
{
  @Autowired
  private UserJPADAO dao;

  @Test
  public void saveUserTest()
  {
    User user = anyUser();
    dao.save(user);
    final User userRetrievedByEmail = dao.findByEmail(user.getEmail());
    assertThat(userRetrievedByEmail, is(equalTo(user)));
  }

  @Test
  public void deleteUser()
  {
    final User user = anyUser();
    dao.save(user);
    assertTrue(dao.exists(user.getId()));
    dao.delete(user.getId());
    assertFalse(dao.exists(user.getId()));
  }


}
