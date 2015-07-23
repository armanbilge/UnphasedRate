package com.armanbilge.unphasedrate;

import java.util.Arrays;

/**
 * @author Arman Bilge
 */
public class TableEntry {

    private final String i;
    private final String j;
    private final double tau;
    public long t;
    public long p;
    public final long[] ps;

    private double dp;
    private final double[] dps;

    public TableEntry(String i, String j, double tau, int N) {
        this.i = i;
        this.j = j;
        this.tau = tau;
        ps = new long[N];
        dps = new double[N];
    }

    public void normalize() {
        dp = p / (double) t;
        Arrays.setAll(dps, i -> ps[i] / (double) t);
    }

    @Override
    public String toString() {
        return String.join("\t", new String[]{i, j, Double.toString(tau), Long.toString(t), Double.toString(dp)}) + "\t" + String.join("\t", Arrays.stream(dps).mapToObj(Double::toString).toArray(String[]::new));
    }

}
