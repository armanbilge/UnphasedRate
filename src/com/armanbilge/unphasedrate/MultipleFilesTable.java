package com.armanbilge.unphasedrate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Arman Bilge
 */
public class MultipleFilesTable {

    public static void main(final String... args) throws FileNotFoundException {
        final Map<String,Double> dates = Dates.parse(new File(args[0]));
        final int N = dates.size();
        final Sequence[] sequences = new Sequence[N];

        final Map<Pair<Character>,Integer> pairs = new HashMap<>();
        final char[] BASES = {'A', 'C', 'G', 'T'};
        for (int i = 0, k = 0; i < BASES.length; ++i) {
            for (int j = i; j < BASES.length; ++j)
                pairs.put(new Pair<>(BASES[i], BASES[j]), k++);
        }

        final TableEntry[] table = new TableEntry[N * (N-1) / 2];

        boolean first = true;
        for (int f = 1; f < args.length; ++f) {
            try (final Scanner sc = new Scanner(new File(args[f]))) {
                final int A, T, G, C;
                {
                    final Scanner l = new Scanner(sc.nextLine().substring("ATGC=".length()));
                    l.useDelimiter(",");
                    A = Utils.nextInt(l);
                    T = Utils.nextInt(l);
                    G = Utils.nextInt(l);
                    C = Utils.nextInt(l);
                }

                for (int i = 0; i < N; ++i) {
                    final Scanner l = new Scanner(sc.nextLine());
                    sequences[i] = new Sequence(l.next(), l.next());
                }

                if (first) {
                    for (int i = 0, k = 0; i < N; ++i) {
                        for (int j = i+1; j < N; ++j) {
                            table[k++] = new TableEntry(sequences[i].getTaxon(), sequences[j].getTaxon(), Math.abs(dates.get(sequences[i].getTaxon()) - dates.get(sequences[j].getTaxon())), 10);
                        }
                    }
                    first = false;
                }


                for (int i = 0, z = 0; i < N; ++i) {
                    for (int j = i+1; j < N; ++j) {
                        final Sequence a = sequences[i];
                        final Sequence b = sequences[j];
                        final double x = Math.abs(dates.get(a.getTaxon()) - dates.get(b.getTaxon()));
                        final String s = a.getSequence();
                        final String t = b.getSequence();
                        final int n = s.length();
                        table[z].t += A + C + G + T;
                        table[z].ps[pairs.get(new Pair<>('A', 'A'))] += A;
                        table[z].ps[pairs.get(new Pair<>('C', 'C'))] += C;
                        table[z].ps[pairs.get(new Pair<>('G', 'G'))] += G;
                        table[z].ps[pairs.get(new Pair<>('T', 'T'))] += T;
                        for (int k = 0; k < n; ++k) {
                            char p = s.charAt(k);
                            char q = t.charAt(k);
                            if (Bases.BASES.contains(p) && Bases.BASES.contains(q)) {
                                table[z].t++;
                                table[z].p += p != q ? 1 : 0;
                                table[z].ps[pairs.get(new Pair<>(p, q))] += 1;
                            }
                        }
                        ++z;
                    }
                }
            }
        }

        System.out.println(String.join("\t", new String[]{"a", "b", "t", "n", "p", "pAA", "pAC", "pAG", "pAT", "pCC", "pCG", "pCT", "pGG", "pGT", "pTT"}));
        Arrays.stream(table).peek(TableEntry::normalize).forEach(System.out::println);

    }

}
