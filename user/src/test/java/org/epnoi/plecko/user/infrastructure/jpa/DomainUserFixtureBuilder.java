package org.epnoi.plecko.user.infrastructure.jpa;

import org.epnoi.plecko.user.domain.Email;
import org.epnoi.plecko.user.domain.Interest;
import org.epnoi.plecko.user.domain.User;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by rgonzalez on 10/03/2017.
 */
public class DomainUserFixtureBuilder
{

  private static final String ANY_USER_EMAIL = "userEmail";
  private static final String ANY_USER_NAME = "userName";
  private static final String ANY_USER_INTERESTS_KEYWORDS = "los keywords";

  private static final String OTHER_USER_EMAIL = "otherUserEmail";
  private static final String OTHER_USER_NAME = "otherUserName";
  private static final String OTHER_USER_INTERESTS_KEYWORDS = "other los keywords";

  public static User anyUser()
  {
    Interest interest = new Interest(ANY_USER_INTERESTS_KEYWORDS);
    User user = User.builder().email(new Email(ANY_USER_EMAIL)).name(ANY_USER_NAME).interests(
      new HashSet<>(Arrays.asList(interest))).build();

    return user;
  }

  public static User otherUser()
  {
    Interest interest = new Interest(OTHER_USER_INTERESTS_KEYWORDS);
    User user = User.builder().email(new Email(OTHER_USER_EMAIL)).name(OTHER_USER_NAME).interests(
      new HashSet<>(Arrays.asList(interest))).build();

    return user;
  }
}
