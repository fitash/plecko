package org.epnoi.plecko.harvester.rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import org.apache.tika.exception.TikaException;
import org.epnoi.plecko.domain.FeedState;
import org.epnoi.plecko.domain.Item;
import org.epnoi.plecko.harvester.rss.extractors.FeedExtractor;
import org.epnoi.plecko.harvester.rss.extractors.TikaExtractor;
import org.xml.sax.SAXException;

import java.io.IOException;

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
                Item item = generatetItem(entry);
                feedState.addItem(item);

            }
            return feedState;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FeedState();
    }

    private Item generatetItem(SyndEntry entry) throws org.epnoi.plecko.domain.exceptions.RetrievalException, TikaException, SAXException, IOException {
        String content = this.contentRetriever.retrieve(entry.getUri());
        String cleanedContent = ContentCleaner.clean(content);

        String description = entry.getDescription().getValue();

        String cleanedDescription = TikaHelper.extractContentFromHTML(description);


        return new Item(entry.getUri(),entry.getLink(),cleanedDescription, cleanedContent);
    }


}
