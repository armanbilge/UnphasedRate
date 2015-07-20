package com.armanbilge.unphasedrate;

import javafx.geometry.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Arman Bilge
 */
public class MultipleFiles {

    public static void main(final String... args) throws FileNotFoundException {
        final Map<String,Double> dates = Dates.parse(new File(args[0]));
        double[] datesArray = dates.entrySet().stream().mapToDouble(Map.Entry::getValue).toArray();
        final int N = dates.size();
        final Counter counter = new Counter();
        final Sequence[] sequences = new Sequence[N];

        for (int f = 1; f < args.length; ++f) {
            try (final Scanner sc = new Scanner(new File(args[f]))) {
                {
                    final Scanner l = new Scanner(sc.nextLine().substring("ATGC=".length()));
                    l.useDelimiter(",");
                    final int n = Utils.nextInt(l) + Utils.nextInt(l) + Utils.nextInt(l) + Utils.nextInt(l);
                    for (int i = 0; i < N; ++i) {
                        for (int j = i+1; j < N; ++j) {
                            counter.add(new Point2D(Math.abs(datesArray[i] - datesArray[j]), 0), n);
                        }
                    }
                }
                for (int i = 0; i < N; ++i) {
                    final Scanner l = new Scanner(sc.nextLine());
                    sequences[i] = new Sequence(l.next(), l.next());
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
