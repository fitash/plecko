package org.epnoi.plecko.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fitash on 5/09/16.
 */
public class Feed {
   private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public Collection<Item> getItems(){
        return items;
    }
}
