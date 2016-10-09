package org.epnoi.plecko.domain.model.modules;

import org.epnoi.plecko.domain.model.Aggregation;
import org.epnoi.plecko.domain.model.DeliveryResponse;

/**
 * Created by rgonza on 28/8/16.
 */

public interface AgregationsDeliverer {
    DeliveryResponse deliver(Aggregation aggregation);
}
