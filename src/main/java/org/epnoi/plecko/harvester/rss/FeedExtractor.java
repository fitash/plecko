package org.epnoi.plecko.harvester.rss;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.http.HttpEntity;
import org.epnoi.plecko.domain.exceptions.RetrievalException;


import java.io.InputStream;

/**
 * Created by fitash on 9/09/16.
 */
public class FeedExtractor implements EntityExtractor<SyndFeed> {
    @Override
    public SyndFeed extract(HttpEntity entity) throws RetrievalException {

        try {
            InputStream stream = entity.getContent();


            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(stream));
            return feed;
        } catch (Exception e) {
            throw new RetrievalException(e.getMessage());
        }
    }
}
