package org.epnoi.plecko.user.infrastructure.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserJPADAO extends CrudRepository<User, Long>
{
  User findByEmail(String email);
}
