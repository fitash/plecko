package org.epnoi.plecko.model.modules;

import org.epnoi.plecko.model.User;

/**
 * Created by rgonza on 30/8/16.
 */
public interface NumberAssigner {
    Long assign(User user);
}
