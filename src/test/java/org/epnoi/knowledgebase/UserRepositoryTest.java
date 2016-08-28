package org.epnoi.knowledgebase;


import org.epnoi.plecko.PleckoApp;
import org.epnoi.plecko.model.Concept;
import org.epnoi.plecko.model.Relation;
import org.epnoi.plecko.model.RelationsBuilder;
import org.epnoi.plecko.storage.KnowledgeBaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PleckoApp.class)
public class UserRepositoryTest {

    @Test
    public void testGraph() {
/*
        Concept conceptA = new Concept("conceptA","http://epnoi/conceptA");
        Concept conceptB = new Concept("conceptB","http://epnoi/conceptB");
        Concept conceptC = new Concept("conceptC","http://epnoi/conceptC");
        Concept conceptD = new Concept("conceptD","http://epnoi/conceptD");
        Concept conceptE = new Concept("conceptE","http://epnoi/conceptE");
        Relation relationB = RelationsBuilder.newBuilder().setRelationType("isB").setSource(conceptA).setTarget(conceptC).build();
        Relation relationA = RelationsBuilder.newBuilder().setRelationType("isA").setSource(conceptA).setTarget(conceptB).build();

        Relation relationC = RelationsBuilder.newBuilder().setRelationType("isC").setSource(conceptB).setTarget(conceptE).build();
        Relation relationD = RelationsBuilder.newBuilder().setRelationType("isD").setSource(conceptC).setTarget(conceptE).build();

        System.out.println("10000000> "+conceptA);
       repository.save(conceptA);
        repository.save(conceptB);

        repository.save(conceptC);
        repository.save(conceptD);
        repository.save(conceptE);

        System.out.println("20000000> "+repository);

       System.out.println("get related----------> "+repository.getRelated(conceptA.getUri()));

        System.out.println("get relations----------> "+repository.getRelations());
       System.out.println("get relations result----> " + repository.getRelationsResults(conceptA.getUri()));
    */

List<String> list = Arrays.asList("a");
assertThat(list,contains(list));
    }

}