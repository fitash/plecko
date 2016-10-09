package org.epnoi.plecko.infrastructure.controllers;

import org.epnoi.plecko.domain.model.FeedState;
import org.epnoi.plecko.domain.model.User;
import org.epnoi.plecko.infrastructure.storage.datastore.UsersDatastoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

@RestController
@EnableAutoConfiguration
public class PelckoController {
    @Autowired
    private UsersDatastoreRepository repository;



    @PostConstruct
    public void init(){

        System.out.println();System.out.println();
        System.out.println("SERVICE UP AND RUNNING!!!");
        System.out.println();System.out.println();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
/*
        String url = "http://www.microsiervos.com/index.xml";
        org.epnoi.plecko.infrastructure.harvester.rss.RSSFeedHarvester harvester = new org.epnoi.plecko.harvester.rss.RSSFeedHarvester();
        FeedState feedState = harvester.harvest(url);
*/

        return "plecko is up and running!!!!";

    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)

    public User user() {
        System.out.println("---------------__> " + this.repository.findAll());
        Iterable<User> users = this.repository.getUser("test@plecko.org");
        System.out.println("---------------__> " + users);
        //return new User("whatevermail", "whatever");
        return users.iterator().next();
    }
}

