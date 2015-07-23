package com.armanbilge.unphasedrate;

/**
 * @author Arman Bilge
 */
public class Pair<T> {
    public final T a;
    public final T b;

    public Pair(T a, T b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            final Pair p = (Pair) obj;
            return (a.equals(p.a) && b.equals(p.b)) || (a.equals(p.b) && b.equals(p.a));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode();
    }
}
