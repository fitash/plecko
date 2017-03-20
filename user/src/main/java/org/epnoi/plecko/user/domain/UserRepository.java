package org.epnoi.plecko.user.domain;

import java.util.Optional;

/**
 * Created by rgonzalez on 06/03/2017.
 */
public interface UserRepository
{
  UserId save(User user);

  Iterable<User> enumerate();

  Optional<User> retrieve(UserId id);

  
}
