package org.epnoi.plecko.service.rest;
import org.epnoi.plecko.domain.FeedState;
import org.epnoi.plecko.harvester.rss.RSSFeedHarvester;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class PelckoService {

    @RequestMapping(path = "/", method = RequestMethod.GET)

    String home() {



        String url = "http://www.microsiervos.com/index.xml";
        RSSFeedHarvester harvester = new RSSFeedHarvester();
        FeedState feedState=harvester.harvest(url);

        return "plecko is up and running!!!!";

    }
}

