package org.epnoi.plecko.model.modules;

import org.epnoi.plecko.model.Item;
import org.epnoi.plecko.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rgonza on 29/8/16.
 */
@Component
public class InformataionItemsRetrieverImpl implements InformationItemsRetriever
{
    @Override
    public List<Item> retrieveInformationItems(User user) {
        return null;
    }
}
