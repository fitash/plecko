package org.epnoi.plecko.harvester.rss.extractors;

import org.apache.http.HttpEntity;
import org.epnoi.plecko.domain.exceptions.RetrievalException;
import org.epnoi.plecko.harvester.rss.TikaHelper;

import java.io.InputStream;


/**
 * Created by fitash on 6/09/16.
 */
public class TikaExtractor implements EntityExtractor<String> {
    public TikaExtractor() {
    }

    public String extract(HttpEntity entity) throws RetrievalException {


        try {
            InputStream is = entity.getContent();
            String content = TikaHelper.extractContentFromHTML(is);
            return content;

        } catch (Exception e) {
            throw new RetrievalException(e.getMessage());
        }


    }
}
