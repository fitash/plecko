package org.epnoi.plecko.infrastructure.harvester.rss.extractors;

import org.apache.http.HttpEntity;
import org.epnoi.plecko.domain.exceptions.RetrievalException;

/**
 * Created by fitash on 8/09/16.
 */
@FunctionalInterface
public interface EntityExtractor<T> {
    T extract(HttpEntity entity) throws RetrievalException;
}
