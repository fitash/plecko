package org.epnoi.plecko.domain.model.modules;

import org.epnoi.plecko.domain.model.Aggregation;
import org.epnoi.plecko.domain.model.DeliveryResponse;
import org.springframework.stereotype.Component;

/**
 * Created by rgonza on 29/8/16.
 */
@Component
public class AggregationsDelivererImpl implements AgregationsDeliverer {
    @Override
    public DeliveryResponse deliver(Aggregation aggregation) {
        System.out.println("---------------------------------------------");
        return null;
    }
}
