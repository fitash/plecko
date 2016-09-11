package org.epnoi.plecko.domain.modules;

import org.epnoi.plecko.domain.Item;
import org.epnoi.plecko.domain.User;

import java.util.List;

/**
 * Created by rgonza on 28/8/16.
 */
public interface InformationItemsRetriever {
    List<Item> retrieveInformationItems(User user);
}
