package org.epnoi.plecko.domain;

import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by rgonza on 20/07/16.
 */
@QueryResult
public class RelatedResult {


    Concept target;
    //Relation relation;


    @Override
    public String toString() {
        return "RelatedResult{" +
                "target=" + target +
                '}';
    }
}
