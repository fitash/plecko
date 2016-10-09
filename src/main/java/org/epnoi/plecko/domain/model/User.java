package org.epnoi.plecko.domain.model;


import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by fitash on 25/08/16.
 */

@Table(value="user")
public class User {

    @PrimaryKey
    private String email;
    private String name;


    public User(String email,String name) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User otherUser = (User) obj;
            return otherUser.getEmail().equals(this.email) && otherUser.getName().equals(otherUser.getName());
        }
        return false;
    }



    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
