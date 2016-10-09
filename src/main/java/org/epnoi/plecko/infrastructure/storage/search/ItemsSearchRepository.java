package org.epnoi.plecko.infrastructure.storage.search;

import org.epnoi.plecko.domain.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by fitash on 21/09/16.
 */
public interface ItemsSearchRepository extends ElasticsearchRepository<Item, String> {
}
