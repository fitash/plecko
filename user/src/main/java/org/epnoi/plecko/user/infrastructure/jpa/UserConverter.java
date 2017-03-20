package org.epnoi.plecko.user.infrastructure.jpa;

import org.epnoi.plecko.user.domain.Email;

import java.util.stream.Collectors;


public class UserConverter
{
  public static User convertToRepository(org.epnoi.plecko.user.domain.User user)
  {
    return User.builder().email(user.getEmail().getAddress()).name(user.getName()).interests(
      user.getInterests().stream().map(UserConverter::convertToRepository).collect(
        Collectors.toSet())).build();
  }

  public static org.epnoi.plecko.user.domain.User convertToDomain(User user)
  {
    if (user != null)
    {
      return org.epnoi.plecko.user.domain.User.builder().name(user.getName()).email(
        new Email(user.getEmail())).interests(
        user.getInterests().stream().map(UserConverter::convertToDomain).collect(Collectors.toSet())).build();
    }
    return null;
  }

  private static Interest convertToRepository(org.epnoi.plecko.user.domain.Interest interest)
  {
    return Interest.builder().keywords(interest.getKeywords()).build();
  }

  private static org.epnoi.plecko.user.domain.Interest convertToDomain(Interest interest)
  {
    return new org.epnoi.plecko.user.domain.Interest(interest.getKeywords());
  }
}
