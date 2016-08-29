package org.epnoi.plecko.model.modules;

import org.epnoi.plecko.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fitash on 25/08/16.
 */
@Component
public class DeliveryHandler {
    @Autowired
    private UsersRepository usersRepository;

    private InformationItemsRetriever informationItemsRetriever;

    private AggregationsCreator aggregationCreator;

    private AgregationsDeliverer agregationsDeliverer;

    private InformationItemsFilterer informationItemsFilterer;

    public void deliver() {
        for (User user : usersRepository.getUsers()) {
            DeliveryResponse deliveryResponse = deliver(user);
        }

    }

    private DeliveryResponse deliver(User user) {
        System.out.println("Delivering content to user " + user.getEmail());
        List<Item> userItems = informationItemsRetriever.retrieveInformationItems(user);
        List<Item> filteredUserItems = informationItemsFilterer.filter(userItems);
        Aggregation userAggregation = aggregationCreator.aggregate(filteredUserItems);

        return   this.agregationsDeliverer.deliver(userAggregation);


    }
}
