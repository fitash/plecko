package org.epnoi.plecko.user.infrastructure.jpa;


import org.epnoi.plecko.user.domain.UserId;
import org.epnoi.plecko.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.epnoi.plecko.user.infrastructure.jpa.UserConverter.convertToDomain;
import static org.epnoi.plecko.user.infrastructure.jpa.UserConverter.convertToRepository;

@Repository
public class UserJPARepository implements UserRepository
{
  @Autowired
  UserJPADAO userDAO;

  @Override
  public UserId save(org.epnoi.plecko.user.domain.User user)
  {
    User savedUser =userDAO.save(convertToRepository(user));
    return  new UserId(savedUser.getId());
  }

  @Override
  public Collection<org.epnoi.plecko.user.domain.User> enumerate()
  {
    List<org.epnoi.plecko.user.domain.User> users = new ArrayList<>();
    userDAO.findAll().forEach(user-> users.add(convertToDomain(user)));
    return users;
  }

  @Override
  public Optional<org.epnoi.plecko.user.domain.User> retrieve(UserId id)
  {
    return Optional.ofNullable(convertToDomain(userDAO.findOne(id.getId())));
  }

  public void remove(org.epnoi.plecko.user.domain.User user)
  {
    final User repoUser = convertToRepository(user);
    User retrievedUser = userDAO.findByEmail(repoUser.getEmail());
    userDAO.delete(retrievedUser);
  }
}
