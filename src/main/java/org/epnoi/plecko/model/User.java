package org.epnoi.plecko.model;

/**
 * Created by fitash on 25/08/16.
 */
public class User {
    private String name;
    private String email;


    public User(String name, String email) {
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
