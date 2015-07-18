package com.armanbilge.unphasedrate;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Arman Bilge
 */
public class Utils {

    public static int nextInt(final Scanner sc) {
        try {
            return sc.nextInt();
        } catch (final InputMismatchException ime) {
            return 0;
        }
    }

}
