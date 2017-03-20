package org.epnoi.plecko.user.infrastructure.jpa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.when;

/**
 * Created by rgonzalez on 12/03/2017.
 */
@RunWith(JUnit4.class)
public class UserJPARepositoryTest
{
  @Mock
  UserJPADAO dao;

  @Before
  public void init()
  {
    MockitoAnnotations.initMocks(this);
    when(dao.findOne(argThat(any()))).thenReturn(new User("email", "name"));
    when(dao.save(argThat())).thenReturn()
  }

  @Test
  public void userDAOIsInvoked()
  {
    System.out.println("???????> "+dao.findOne(2L));
  }


}