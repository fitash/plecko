package org.epnoi.plecko;

import org.epnoi.plecko.config.Profiles;
import org.epnoi.plecko.domain.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.epnoi.plecko.infrastructure.persistence.search.*;

/**
 * Created by fitash on 20/09/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PleckoApp.class)
@ActiveProfiles({Profiles.SEARCH, Profiles.DATASTORE})
public class ItemsSearchRepositoryTest {

    @Autowired
    private ItemsSearchRepository itemsRepository;

    @Before
    public void init() {
        itemsRepository.deleteAll();
    }

    @Test(timeout = 5000L)
    public void storageAndRetrieval() {

        Item item = new Item("uri", "url", "content", "description");

        this.itemsRepository.save(item);

        Item retrievedItem = this.itemsRepository.findOne(item.getUri());

        System.out.println("------------> " + retrievedItem);
        assertThat(item, is(equalTo(retrievedItem)));

    }


}
