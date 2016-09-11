package org.epnoi.plecko.domain.modules;

import org.epnoi.plecko.domain.Aggregation;
import org.epnoi.plecko.domain.DeliveryResponse;

/**
 * Created by rgonza on 28/8/16.
 */

public interface AgregationsDeliverer {
    DeliveryResponse deliver(Aggregation aggregation);
}
