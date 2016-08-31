package org.epnoi.plecko.model.modules;

import org.epnoi.plecko.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by fitash on 25/08/16.
 */
@Component
public class DeliveryHandler {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private InformationItemsRetriever informationItemsRetriever;
    @Autowired
    private AggregationsCreator aggregationCreator;
    @Autowired
    private AgregationsDeliverer agregationsDeliver;
    @Autowired
    private InformationItemsFilterer informationItemsFilterer;

    @PostConstruct
    public void init(){
        System.out.println("--------> "+aggregationCreator);
    }


    public void deliver() {
        for (User user : this.usersRepository.getUsers()) {
            DeliveryResponse deliveryResponse = deliver(user);
        }

    }



    private DeliveryResponse deliver(User user) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Delivering content to user " + user);
        System.out.println();
        System.out.println();System.out.println();


        List<Item> userItems = this.informationItemsRetriever.retrieveInformationItems(user);
        List<Item> filteredUserItems = this.informationItemsFilterer.filter(userItems);
        Aggregation userAggregation = this.aggregationCreator.aggregate(filteredUserItems);

        return   this.agregationsDeliver.deliver(userAggregation);

    }
}
