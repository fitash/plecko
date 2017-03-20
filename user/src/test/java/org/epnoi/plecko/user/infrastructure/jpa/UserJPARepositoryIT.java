package org.epnoi.plecko.user.infrastructure.jpa;

import org.epnoi.plecko.user.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.epnoi.plecko.user.infrastructure.jpa.UserConverter.convertToDomain;
import static org.epnoi.plecko.user.infrastructure.jpa.UserFixtureBuilder.anyUser;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;




@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserJPARepositoryIT
{
  @Autowired
  UserJPARepository repository;

  @Test
  public void checkIfIsAutowired()
  {
    assertNotNull(this.repository);
    assertTrue(repository instanceof UserJPARepository);
  }

  @Test
  public void saveUser()
  {
    org.epnoi.plecko.user.domain.User user = convertToDomain(anyUser());
    assertThat(repository.enumerate(), not(hasItem(user)));
    repository.save(user);
    assertThat(repository.enumerate(), hasItem(user));
  }

  @Test
  public void deleteUser()
  {
    org.epnoi.plecko.user.domain.User user = convertToDomain(anyUser());
    repository.save(user);
    assertThat(repository.enumerate(), hasItem(user));
    repository.remove(user);
    assertThat(repository.enumerate(), not(hasItem(user)));
  }

}