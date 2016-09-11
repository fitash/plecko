package org.epnoi.plecko.domain.modules;

import org.epnoi.plecko.domain.Aggregation;
import org.epnoi.plecko.domain.Item;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rgonza on 28/8/16.
 */
@Component
public class AggregationsCreatorImpl implements AggregationsCreator {

    @Override
    public Aggregation aggregate(List<Item> filteredUserItems) {
        return null;
    }
}
