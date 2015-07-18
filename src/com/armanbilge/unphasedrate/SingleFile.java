package com.armanbilge.unphasedrate;

import javafx.geometry.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Arman Bilge
 */
public class SingleFile {

    public static void main(final String... args) throws FileNotFoundException {
        final Map<String,Double> dates = Dates.parse(new File(args[0]));
        double[] datesArray = dates.entrySet().stream().mapToDouble(Map.Entry::getValue).toArray();
        final int N = dates.size();
        final Counter counter = new Counter();
        final Sequence[] sequences = new Sequence[N];

        try (final Scanner sc = new Scanner(new File(args[1]))) {
            while (sc.hasNext()) {
                {
                    final Scanner l = new Scanner(sc.nextLine());
                    l.useDelimiter(",");
                    l.next();
                    final String taxon = l.next();
                    final int n = Utils.nextInt(l) + Utils.nextInt(l) + Utils.nextInt(l) + Utils.nextInt(l);
                    for (int i = 0; i < N; ++i) {
                        for (int j = i; j < N; ++j) {
                            counter.add(new Point2D(Math.abs(datesArray[i] - datesArray[j]), 0), n);
                        }
                    }
                    sequences[0] = new Sequence(taxon, l.next());
                }
                for (int i = 1; i < N; ++i) {
                    final Scanner l = new Scanner(sc.nextLine());
                    l.useDelimiter(",");
                    l.next();
                    final String taxon = l.next();
                    l.next(); l.next(); l.next(); l.next();
                    sequences[i] = new Sequence(taxon, l.next());
                }
                for (int i = 0; i < N; ++i) {
                    for (int j = i+1; j < N; ++j) {
                        final Sequence a = sequences[i];
                        final Sequence b = sequences[j];
                        final double x = Math.abs(dates.get(a.getTaxon()) - dates.get(b.getTaxon()));
                        final String s = a.getSequence();
                        final String t = b.getSequence();
                        final int n = s.length();
                        for (int k = 0; k < n; ++k) {
                            char p = s.charAt(k);
                            char q = t.charAt(k);
                            if (Bases.BASES.contains(p) && Bases.BASES.contains(q))
                                counter.add(new Point2D(x, p == q ? 0 : 1));
                        }
                    }
                }
            }
        }
        System.out.println(counter.toDictionary());
    }

}
