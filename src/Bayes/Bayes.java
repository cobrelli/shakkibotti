//P(“AKUSSA VIRTAA”)=0.9
//P(“RADIO” | “AKUSSA VIRTAA”)=0.9 P(“RADIO” | ¬”AKUSSA VIRTAA”)=0
//P(“SYTYTYS” | “AKUSSA VIRTAA”) = 0.95 P(“SYTYTYS” | ¬”AKUSSA VIRTAA”)=0
//P(“BENSAA”) = 0.95
//P(“KÄYNNISTYY” | “AKUSSA VIRTAA” JA “BENSAA”) = 0.99 P(“KÄYNNISTYY” | ¬”A” TAI ¬”B”) = 0
//P(“LIIKKUU” | “KÄYNNISTYY”) = 0.99 P(“LIIKKUU” | ¬”KÄYNNISTYY”) =
package Bayes;

import java.util.Random;

/**
 *
 * @author Cobrelli
 */
public class Bayes {

    public static int aJaLNolla;
    public static int lJaBNolla;
    public static int kNollaAYksi;
    public static int kNollaBNollaAYksi;
    public static Random rand;

    public static void main(String[] args) {
        rand = new Random();

        for (int i = 0; i < 100000; i++) {
            generoi();
        }
        System.out.println("a ja l nollat : " + (double) aJaLNolla / 100000 + "%");
        System.out.println("l ja b nollat : " + (double) lJaBNolla / 100000 + "%");
        System.out.println("k nolla, a yksi : " + (double) kNollaAYksi / 100000 + "%");
        System.out.println("k b nollat, a yksi : " + (double) kNollaBNollaAYksi / 100000 + "%");
    }

    public static void generoi() {
        int a = 0;
        int r = 0;
        int s = 0;
        int b = 0;
        int k = 0;
        int l = 0;

        //generoidaan A
        if ((rand.nextInt(100) + 1) <= 90) {
            a = 1;
        }
        //generoidaan R
        if (a != 0) {
            if (rand.nextInt(100) + 1 <= 90) {
                r = 1;
            }
        }
        //generoidaan S
        if (a != 0) {
            if (rand.nextInt(100) + 1 <= 95) {
                s = 1;
            }
        }
        //generoidaan B
        if (rand.nextInt(100) + 1 <= 95) {
            b = 1;
        }
        //generoidaan K
        if (s != 0 && b != 0) {
            if (rand.nextInt(100) + 1 <= 99) {
                k = 1;
            }
        }
        //generoidaan L
        if (k != 0) {
            if (rand.nextInt(100) + 1 <= 99) {
                l = 1;
            }
        }

        //suoritetaan kirjanpito tarkastelua varten

        if (l == 0 && a == 0) {
            aJaLNolla++;
        }
        if (l == 0 && b == 0) {
            lJaBNolla++;
        }
        if (k == 0 && a == 1) {
            kNollaAYksi++;
        }
        if (k == 0 && b == 0 && a == 1) {
            kNollaBNollaAYksi++;
        }
    }
}
