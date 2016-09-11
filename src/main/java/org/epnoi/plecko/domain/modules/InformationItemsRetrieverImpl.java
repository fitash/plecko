package org.epnoi.plecko.domain.modules;

import org.epnoi.plecko.domain.Item;
import org.epnoi.plecko.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rgonza on 29/8/16.
 */
@Component
public class InformationItemsRetrieverImpl implements InformationItemsRetriever
{
    @Override
    public List<Item> retrieveInformationItems(User user) {
        return null;
    }
}
