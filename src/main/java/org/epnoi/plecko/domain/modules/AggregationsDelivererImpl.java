package org.epnoi.plecko.domain.modules;

import org.epnoi.plecko.domain.Aggregation;
import org.epnoi.plecko.domain.DeliveryResponse;
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
