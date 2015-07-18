package com.armanbilge.unphasedrate;


import javafx.geometry.Point2D;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Arman Bilge
 */
public class Counter {

    private final Map<Point2D, Long> counts = new HashMap<>();

    public void add(final Point2D p) {
        add(p, 1);
    }

    public void add(final Point2D p, long l) {
        counts.merge(p, l, Math::addExact);
    }

    public long size() {
        return counts.entrySet().stream().mapToLong(Map.Entry::getValue).sum();
    }

    public String toDictionary() {
        return "{" + String.join(", ", (Iterable<String>) counts.entrySet().stream().map(entry -> "(" + entry.getKey().getX() + "," + entry.getKey().getY() + "): " + entry.getValue())::iterator) + "}";
    }

}
