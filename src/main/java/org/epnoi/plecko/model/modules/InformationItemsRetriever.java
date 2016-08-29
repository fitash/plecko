package org.epnoi.plecko.model.modules;

import org.epnoi.plecko.model.Item;
import org.epnoi.plecko.model.User;

import java.util.List;

/**
 * Created by rgonza on 28/8/16.
 */
public interface InformationItemsRetriever {
    List<Item> retrieveInformationItems(User user);
}
