package org.epnoi.plecko.model.modules;

import org.epnoi.plecko.model.Aggregation;
import org.epnoi.plecko.model.DeliveryResponse;
import org.springframework.stereotype.Component;

/**
 * Created by rgonza on 28/8/16.
 */
@Component
public interface AgregationsDeliverer {
    DeliveryResponse deliver(Aggregation aggregation);
}
