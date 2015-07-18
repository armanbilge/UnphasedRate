package com.armanbilge.unphasedrate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Arman Bilge
 */
public class Dates {

    public static Map<String,Double> parse(final File file) throws FileNotFoundException {
        final Map<String,Double> dates = new HashMap<>();
        try (final Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                dates.put(sc.next(), sc.nextDouble());
            }
        }
        return dates;
    }

}
