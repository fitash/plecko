package org.epnoi.plecko.harvester.rss;


import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.epnoi.plecko.domain.exceptions.RetrievalException;


import java.io.InputStream;


/**
 * Created by fitash on 3/09/16.
 */
public class RSSFeedRetriever {

    public SyndFeed retrieve(String url) throws RetrievalException {
        SyndFeed feed;
        try {
            //String url = "http://rss.slashdot.org/Slashdot/slashdot";
            try (CloseableHttpClient client = HttpClients.createMinimal()) {
                HttpUriRequest method = new HttpGet(url);
                try (CloseableHttpResponse response = client.execute(method);
                     InputStream stream = response.getEntity().getContent()) {


                    SyndFeedInput input = new SyndFeedInput();
                    feed = input.build(new XmlReader(stream));

                    return feed;
                }
            }


        } catch (final Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }

        return null;


    }

    public static void main(String[] args) throws RetrievalException {

    String url = "http://rss.slashdot.org/Slashdot/slashdot";
    RSSFeedRetriever rssRetriever = new RSSFeedRetriever();
    rssRetriever.retrieve(url);

}

}

