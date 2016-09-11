package org.epnoi.plecko.harvester.rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import org.epnoi.plecko.domain.FeedState;
import org.epnoi.plecko.domain.Item;
import org.epnoi.plecko.domain.exceptions.RetrievalException;

/**
 * Created by fitash on 5/09/16.
 */
public class RSSFeedHarvester {


    private TikaExtractor tikaExtractor;
    private HTTPRetriever<SyndFeed> feedRetriever;
    private HTTPRetriever<String> contentRetriever;




    public RSSFeedHarvester(){

        //this.tikaExtractor = new TikaExtractor();
        this.contentRetriever= new HTTPRetriever<>(new TikaExtractor());
        this.feedRetriever = new HTTPRetriever<>(new FeedExtractor());

    }



    public FeedState harvest(String url) {
        try {
            FeedState feedState = new FeedState();
            SyndFeed syndFeed = feedRetriever.retrieve(url);
            for (SyndEntry entry : syndFeed.getEntries()) {


                String content = this.contentRetriever.retrieve(entry.getUri());
                String cleanedContent = ContentCleaner.clean(content);

                System.out.println();
                System.out.println("AQUI----------------------------------------------------------------------------");
                System.out.println();
                //System.out.println(cleanedContent);
                Item item = new Item(entry.getUri(),entry.getLink(), cleanedContent);
                String value = entry.getDescription().getValue();
                value = ContentCleaner.clean(value);
                System.out.println("--> "+ value);
                feedState.addItem(item);

            }
            return feedState;
        } catch (RetrievalException e) {
            e.printStackTrace();
        }
        return new FeedState();
    }

    public static void main(String[] args) {
       // String url = "http://rss.slashdot.org/Slashdot/slashdot";

        String url = "http://www.microsiervos.com/index.xml";
        RSSFeedHarvester harvester = new RSSFeedHarvester();
        FeedState feedState=harvester.harvest(url);
       // System.out.println("------> "+feedState);
    }
}
