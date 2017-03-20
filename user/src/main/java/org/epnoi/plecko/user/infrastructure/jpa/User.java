package org.epnoi.plecko.user.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


/**
 * Created by fitash on 25/08/16.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User
{
  @Column(name = "ID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "user_id")
  private Set<Interest> interests;


  public User(String email, String name)
  {
    this.name = name;
    this.email = email;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof User)
    {
      User otherUser = (User) obj;
      return otherUser.getEmail().equals(this.email) && otherUser.getName().equals(otherUser.getName());
    }
    return false;
  }


  @Override
  public String toString()
  {
    return "User{" + "name='" + name + '\'' + ", email='" + email + '\'' + '}';
  }
}
