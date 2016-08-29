package org.epnoi.plecko.model.modules;

import org.epnoi.plecko.model.Item;

import java.util.List;

/**
 * Created by rgonza on 28/8/16.
 */
public interface InformationItemsFilterer {

    List<Item> filter(List<Item> userItems);
}
