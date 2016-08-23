package org.epnoi.knowledgebase.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rgonza on 10/07/16.
 */
@NodeEntity
public class Concept {
    @GraphId
    private Long id;

    private String name;


    private String uri;

    @Relationship(type = "RELATION", direction = "OUTGOING")
    private List<Relation> relations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Concept(String name, String uri) {
        this.uri = uri;
        this.name = name;
    }


    public Concept() {

    }

    public void addRelation(Relation relation) {
        this.relations.add(relation);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    @Override
    public String toString() {
        return "Concept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
               ", relations=" + relations +
                '}';
    }
}
