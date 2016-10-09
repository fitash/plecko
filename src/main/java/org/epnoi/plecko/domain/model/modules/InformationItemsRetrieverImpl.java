package org.epnoi.plecko.domain.model.modules;

import org.epnoi.plecko.domain.model.Item;
import org.epnoi.plecko.domain.model.User;
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
