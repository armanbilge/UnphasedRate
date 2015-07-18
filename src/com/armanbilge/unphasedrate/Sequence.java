package com.armanbilge.unphasedrate;

/**
 * @author Arman Bilge
 */
public class Sequence {

    private final String taxon;
    private final String sequence;

    public Sequence(final String taxon, final String sequence) {
        this.taxon = taxon;
        this.sequence = sequence;
    }

    public String getTaxon() {
        return taxon;
    }

    public String getSequence() {
        return sequence;
    }
}
