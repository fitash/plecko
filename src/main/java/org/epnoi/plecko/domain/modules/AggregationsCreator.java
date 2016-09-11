package org.epnoi.plecko.domain.modules;

import org.epnoi.plecko.domain.Aggregation;
import org.epnoi.plecko.domain.Item;

import java.util.List;

/**
 * Created by rgonza on 28/8/16.
 */
public interface AggregationsCreator {
    Aggregation aggregate(List<Item> filteredUserItems);
}
