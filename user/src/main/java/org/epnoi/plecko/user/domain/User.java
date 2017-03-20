package org.epnoi.plecko.user.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class User
{
  private String name;
  private final Email email;
  private Set<Interest> interests;
}
