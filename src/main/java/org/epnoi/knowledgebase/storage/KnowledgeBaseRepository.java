package org.epnoi.knowledgebase.storage;

import org.epnoi.knowledgebase.model.Concept;
import org.epnoi.knowledgebase.model.RelatedResult;
import org.epnoi.knowledgebase.model.Relation;
import org.neo4j.cypher.internal.compiler.v2_2.functions.Str;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Created by rgonza on 10/07/16.
 */
@Repository
public interface KnowledgeBaseRepository extends GraphRepository<Concept> {
   @Query("MATCH (source:Concept)-[ r:RELATION]-> (target:Concept) "+
    "WHERE  source.uri={uri} "+
    "RETURN target")

 //   @Query("MATCH (target:Concept) RETURN target")

    Iterable<Concept> getRelated(@Param("uri") String uri);


    @Query("MATCH (source:Concept)-[ r:RELATION]-> (target:Concept) "+
            "WHERE source.uri=source && target.uri=target "+
            "RETURN r "
    )
    Iterable<Relation> getRelations(@Param("source")String source, @Param("target")String target);


 @Query("MATCH (source:Concept)-[ r:RELATION]-> (target:Concept) "+

         "RETURN r "
 )
 Iterable<Relation> getRelations();


    @Query("MATCH (source:Concept)-[ relation:RELATION]-> (target:Concept) "+
            "WHERE  source.uri={uri} "+
            "RETURN target")
    Iterable<RelatedResult> getRelationsResults(@Param("uri")String source);




}
