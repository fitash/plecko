package org.epnoi.plecko.model.modules;

import org.epnoi.plecko.model.Aggregation;
import org.epnoi.plecko.model.DeliveryResponse;

/**
 * Created by rgonza on 28/8/16.
 */
public interface AgregationsDeliverer {
    DeliveryResponse deliver(Aggregation aggregation);
}
