package org.epnoi.plecko;

import org.epnoi.plecko.domain.model.Aggregation;
import org.epnoi.plecko.domain.model.Item;
import org.epnoi.plecko.domain.model.User;
import org.epnoi.plecko.domain.exceptions.UserNotFoundException;
import org.epnoi.plecko.domain.model.modules.*;
import org.epnoi.plecko.domain.model.modules.repositories.UsersRepository;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by fitash on 25/08/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PleckoApp.class)
public class DeliveryCycleTest {

    public static final String TEST_USER_NAME = "test";
    public static final String TEST_USER_EMAIL = "test@plecko.org";
    @Mock
    UsersRepository usersRepository;
    @InjectMocks
    DeliveryHandler deliveryHandler;
    @Mock
    private InformationItemsRetriever informationItemsRetriever;
    @Mock
    private AggregationsCreator aggregationCreator;
    @Mock
    private AgregationsDeliverer agregationsDeliver;
    @Mock
    private InformationItemsFilterer informationItemsFilterer;
    @Mock
    private NumberAssigner numberAssigner;

    User userA = new User("A", "A");
    User userB = new User("B", "B");

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Before
    public void init() throws UserNotFoundException {
        MockitoAnnotations.initMocks(this);




        Item itemA = new Item("uriA","uriA","descriptionA", "contentA");
        Item itemB = new Item("uriB","uriA","descriptionB", "contentB");

        List<Item> items = Arrays.asList(itemA, itemB);

        Aggregation aggregation = new Aggregation();
        aggregation.setItems(items);

        User testUser = new User( TEST_USER_EMAIL,TEST_USER_NAME);
        when(usersRepository.getUser("test@plecko.org")).thenReturn(testUser);
        when(usersRepository.getUsers()).thenReturn(Arrays.asList(testUser));

        when(this.aggregationCreator.aggregate(anyList())).thenReturn(null);
        when(this.agregationsDeliver.deliver(argThat((instanceOf(Aggregation.class))))).thenReturn(null);


        when(this.numberAssigner.assign(argThat(HasPropertyWithValue.hasProperty("name", is(equalTo("A")))))).thenReturn(1L).thenReturn(2L).thenThrow(new ChangeSetPersister.NotFoundException());
        when(this.numberAssigner.assign(argThat(HasPropertyWithValue.hasProperty("name", is(equalTo("B")))))).thenReturn(4L);

    }

    @Test
    public void deliverTest() {
        deliveryHandler.deliver();
    }

    @Test
    public void deliverTest2() throws UserNotFoundException {
        deliveryHandler.deliver();

        //  System.out.println(usersRepository.getUser("test@plecko.org"));
        // System.out.println(usersRepository.getUser("test@plecko.org"));


        verify(agregationsDeliver).deliver(argThat(anyOf(hasProperty("items"), nullValue())));
    }


    @Test
    public void deliverTest3() {
        expectedException.expect(ChangeSetPersister.NotFoundException.class);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("=========> "+this.numberAssigner.assign(userB));
        System.out.println("=========> "+this.numberAssigner.assign(userA));
        System.out.println("=========> "+this.numberAssigner.assign(userB));
        System.out.println("=========> "+this.numberAssigner.assign(userA));
        System.out.println("=========> "+this.numberAssigner.assign(userA));
        System.out.println();

        System.out.println();System.out.println();


    }
}
