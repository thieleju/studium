package Übungsaufgaben;

public class Aufgabe7 {

    // 7.1
    public static int getDigitsCount_Recursive(int z) {
        if (z <= 0) {
            return 0;
        }
        return 1 + getDigitsCount_Recursive(z / 10);
    }

    // 7.2
    public static int getGGT_Recursive(int a, int b) {
        if (b == 0)
            return a;
        return getGGT_Recursive(b, a % b);
    }

    // 7.3
    // Baum
    // aum + B
    // ( um + a ) + B
    // (( m + u ) + a ) + B
    // ((( m ) + u ) + a ) + B
    public static String reverseString_Recursive(String str) {
        if (str.length() <= 1)
            return str;

        // System.out.println(str.substring(1) + " / " + str.charAt(0));
        return reverseString_Recursive(str.substring(1)) + str.charAt(0);
    }

    // 7.4
    public static int checksum_Recursive(int z) {
        if (z == 0)
            return 0;
        return checksum_Recursive(z / 10) + z % 10;
    }

    // 7.5
    // machWas (a, a, a); zulässig
    // machWas (a, a, b); zulässig
    // machWas (a, b, a); zulässig
    // machWas (a, b, b); zulässig
    // machWas (b, b, b); NICHT zulässig
    // machWas (b, b, a); NICHT zulässig
    // machWas (b, a, b); zulässig
    // machWas (b, a, a); zulässig
}