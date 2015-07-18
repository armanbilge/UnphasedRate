package com.armanbilge.unphasedrate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arman Bilge
 */
public class Bases {

    public static final Set<Character> BASES;

    static {
        final Set<Character> bases = new HashSet<>(4);
        bases.add('A');
        bases.add('C');
        bases.add('G');
        bases.add('T');
        BASES = Collections.unmodifiableSet(bases);
    }

}
