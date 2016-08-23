package org.epnoi.knowledgebase.model;

/**
 * Created by rgonza on 13/07/16.
 */
public class RelationsBuilder {

    public static interface RelationStep {
        SourceStep setRelationType(String type);
    }


    public static interface SourceStep {
        TargetStep setSource(Concept concept);
    }

    public static interface TargetStep {
        BuildStep setTarget(Concept concept);
    }

    public static interface BuildStep {
        public Relation build();
    }


    public static RelationStep newBuilder() {
        return new SteppedBuilder();
    }

    private static class SteppedBuilder implements TargetStep, SourceStep, RelationStep, BuildStep {
        private Concept sourceConcept;
        private Concept targetConcept;
        private String type;

        public Relation build() {
            Relation relation = new Relation();
            relation.setSource(sourceConcept);
            relation.setTarget(targetConcept);
            relation.setType(type);
            sourceConcept.addRelation(relation);
           // targetConcept.addRelation(relation);
            return relation;
        }

        @Override
        public SourceStep setRelationType(String type) {

            return this;
        }

        @Override
        public TargetStep setSource(Concept concept) {
            this.sourceConcept = concept;
            return this;
        }

        @Override
        public BuildStep setTarget(Concept concept) {
            this.targetConcept = concept;
            return this;
        }


        private SteppedBuilder() {

        }

    }

}





