package org.epnoi.plecko.user.infrastructure.jpa;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by rgonzalez on 09/03/2017.
 */
public class UserFixtureBuilder
{

  private static final String ANY_USER_EMAIL = "userEmail";
  private static final String ANY_USER_NAME = "userName";
  private static final String ANY_USER_INTERESTS_KEYWORDS = "los keywords";

  private static final String OTHER_USER_EMAIL = "otherUserEmail";
  private static final String OTHER_USER_NAME = "otherUserName";
  private static final String OTHER_USER_INTERESTS_KEYWORDS = "other los keywords";

  public static User anyUser()
  {
    Interest interest = Interest.builder().keywords(ANY_USER_INTERESTS_KEYWORDS).build();
    User user = User.builder().email(ANY_USER_EMAIL).name(ANY_USER_NAME).interests(
      new HashSet<>(Arrays.asList(interest))).build();
    
    return user;
  }

  public static User otherUser()
  {
    Interest interest = Interest.builder().keywords(OTHER_USER_INTERESTS_KEYWORDS).build();
    User user = User.builder().email(OTHER_USER_EMAIL).name(OTHER_USER_NAME).interests(
      new HashSet<>(Arrays.asList(interest))).build();

    return user;
  }
}
