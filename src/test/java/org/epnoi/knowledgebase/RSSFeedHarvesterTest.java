package org.epnoi.knowledgebase;

import org.epnoi.plecko.PleckoApp;
import org.epnoi.plecko.domain.FeedState;
import org.epnoi.plecko.harvester.rss.RSSFeedHarvester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by fitash on 11/09/16.
 */
@RunWith(MockitoJUnitRunner.class)

public class RSSFeedHarvesterTest {
  @Test
    public void test(){
        // String url = "http://rss.slashdot.org/Slashdot/slashdot";

        String url = "http://www.microsiervos.com/index.xml";
        RSSFeedHarvester harvester = new RSSFeedHarvester();
        FeedState feedState=harvester.harvest(url);
        // System.out.println("------> "+feedState);
    }
}
