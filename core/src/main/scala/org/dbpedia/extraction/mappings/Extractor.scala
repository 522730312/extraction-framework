package org.dbpedia.extraction.mappings

import org.dbpedia.extraction.destinations.{Quad,Dataset}

/**
 * TODO: generic type may not be optimal.
 */
trait Extractor[-N] {

    var state = ExtractorState.Instantiated
    /**
     * @param page The source node
     * @param subjectUri The subject URI of the generated triples
     * @return A graph holding the extracted data
     */
    def extract(input: N, subjectUri: String): Seq[Quad]

    /**
      * when extractor has a pre-phase
      */
    def initializeExtractor(): Unit = {state = ExtractorState.Initialized}

    /**
      * when extractor needs some finalization
      */
    def finalizeExtractor(): Unit = {state = ExtractorState.Finalized}

    /**
     * Datasets generated by this extractor. Used for serialization. If a mapping implementation
     * does not return all datasets it produces, serialization may fail.
     */
    val datasets: Set[Dataset]

}

object ExtractorState extends Enumeration {
    val Instantiated,Initialized,Finalized = Value
}