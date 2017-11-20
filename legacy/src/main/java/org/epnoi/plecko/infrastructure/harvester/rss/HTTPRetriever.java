package org.epnoi.plecko.infrastructure.harvester.rss;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.epnoi.plecko.domain.exceptions.RetrievalException;
import org.epnoi.plecko.infrastructure.harvester.rss.extractors.EntityExtractor;


import java.io.IOException;

/**
 * Created by fitash on 8/09/16.
 */
public class HTTPRetriever<T> {

    private EntityExtractor<T> extractor;

    public HTTPRetriever(EntityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public T retrieve(String url) throws RetrievalException {
           CloseableHttpClient client=null;

            try {
               client= HttpClients.custom()
                        .setRedirectStrategy(new DefaultRedirectStrategy())
                        .build();


                HttpUriRequest method = new HttpGet(url);

                    CloseableHttpResponse response = client.execute(method);
                     T item= extractor.extract(response.getEntity());
                    return item;

        } catch ( Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }finally {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RetrievalException(e.getMessage());
                }
            }

        return null;


    }
}
