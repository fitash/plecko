package org.epnoi.plecko.domain.model.modules;

import org.epnoi.plecko.domain.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rgonza on 29/8/16.
 */
@Component
public class InformationItemsFiltererImpl implements InformationItemsFilterer {

    @Override
    public List<Item> filter(List<Item> userItems) {
        return null;
    }
}
