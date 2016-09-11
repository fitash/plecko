package org.epnoi.plecko.domain.modules;

import org.epnoi.plecko.domain.User;

/**
 * Created by rgonza on 30/8/16.
 */
public interface NumberAssigner {
    Long assign(User user);
}
