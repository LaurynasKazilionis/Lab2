package laborai.demo;

import laborai.studijosktu.Ks;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.SortedSetADTx;
import laborai.studijosktu.SetADT;
import laborai.studijosktu.BstSetKTUx;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

/*
 * Aibės testavimas be Swing'o
 *
 */
public class AutoTestai {

    static Sunkvežimis[] autoBaze;
    static SortedSetADTx<Sunkvežimis> aSerija = new BstSetKTUx(new Sunkvežimis(), Sunkvežimis.pagalKaina);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        aibėsTestas();
    }

    static SortedSetADTx generuotiAibe(int kiekis, int generN) {
        autoBaze = new Sunkvežimis[generN];
        for (int i = 0; i < generN; i++) {
            autoBaze[i] = new Sunkvežimis.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(autoBaze));
        aSerija.clear();
        for (int i = 0; i < kiekis; i++) {
            aSerija.add(autoBaze[i]);
        }
        return aSerija;
    }

    public static void aibėsTestas() throws CloneNotSupportedException {
        Sunkvežimis a1 = new Sunkvežimis("Renault", "Laguna", 1997, 50000, 1700);
        Sunkvežimis a2 = new Sunkvežimis.Builder()
                .markė("Renault")
                .modelis("Megane")
                .gamMetai(2001)
                .rida(20000)
                .kaina(3500)
                .build();
        Sunkvežimis a3 = new Sunkvežimis.Builder().buildRandom();
        Sunkvežimis a4 = new Sunkvežimis("Renault Laguna 2001 115900 700");
        Sunkvežimis a5 = new Sunkvežimis("Renault Megane 1946 365100 9500");
        Sunkvežimis a6 = new Sunkvežimis("Honda   Civic  2001  36400 80.3");
        Sunkvežimis a7 = new Sunkvežimis("Renault Laguna 2001 115900 7500");
        Sunkvežimis a8 = new Sunkvežimis("Renault Megane 1946 365100 950");
        Sunkvežimis a9 = new Sunkvežimis("Honda   Civic  2007  36400 850.3");

        Sunkvežimis[] autoMasyvas = {a9, a7, a8, a5, a1, a6};

        Ks.oun("Auto Aibė:");
        SortedSetADTx<Sunkvežimis> autoAibe = new BstSetKTUx(new Sunkvežimis());

        for (Sunkvežimis a : autoMasyvas) {
            autoAibe.add(a);
            Ks.oun("Aibė papildoma: " + a + ". Jos dydis: " + autoAibe.size());
        }
        Ks.oun("");
        Ks.oun(autoAibe.toVisualizedString(""));

        SortedSetADTx<Sunkvežimis> autoAibeKopija
                = (SortedSetADTx<Sunkvežimis>) autoAibe.clone();

        autoAibeKopija.add(a2);
        autoAibeKopija.add(a3);
        autoAibeKopija.add(a4);
        Ks.oun("Papildyta autoaibės kopija:");
        Ks.oun(autoAibeKopija.toVisualizedString(""));

        a9.setRida(10000);

        Ks.oun("Originalas:");
        Ks.ounn(autoAibe.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibėje?");
        for (Sunkvežimis a : autoMasyvas) {
            Ks.oun(a + ": " + autoAibe.contains(a));
        }
        Ks.oun(a2 + ": " + autoAibe.contains(a2));
        Ks.oun(a3 + ": " + autoAibe.contains(a3));
        Ks.oun(a4 + ": " + autoAibe.contains(a4));
        Ks.oun("");

        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
        for (Sunkvežimis a : autoMasyvas) {
            Ks.oun(a + ": " + autoAibeKopija.contains(a));
        }
        Ks.oun(a2 + ": " + autoAibeKopija.contains(a2));
        Ks.oun(a3 + ": " + autoAibeKopija.contains(a3));
        Ks.oun(a4 + ": " + autoAibeKopija.contains(a4));
        Ks.oun("");

        Ks.oun("Elementų šalinimas iš kopijos. Aibės dydis prieš šalinimą:  " + autoAibeKopija.size());
        for (Sunkvežimis a : new Sunkvežimis[]{a2, a1, a9, a8, a5, a3, a4, a2, a7, a6, a7, a9}) {
            autoAibeKopija.remove(a);
            Ks.oun("Iš autoaibės kopijos pašalinama: " + a + ". Jos dydis: " + autoAibeKopija.size());
        }
        Ks.oun("");

        Ks.oun("Automobilių aibė su iteratoriumi:");
        Ks.oun("");
        for (Sunkvežimis a : autoAibe) {
            Ks.oun(a);
        }
        Ks.oun("");
        Ks.oun("Automobilių aibė AVL-medyje:");
        SortedSetADTx<Sunkvežimis> autoAibe3 = new AvlSetKTUx(new Sunkvežimis());
        for (Sunkvežimis a : autoMasyvas) {
            autoAibe3.add(a);
        }
        Ks.ounn(autoAibe3.toVisualizedString(""));

        Ks.oun("Automobilių aibė su iteratoriumi:");
        Ks.oun("");
        for (Sunkvežimis a : autoAibe3) {
            Ks.oun(a);
        }

        Ks.oun("");
        Ks.oun("Automobilių aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator iter = autoAibe3.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Automobilių aibės toString() metodas:");
        Ks.ounn(autoAibe3);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        autoAibe.clear();
        autoAibe3.clear();

        Ks.oun("");
        Ks.oun("Automobilių aibė DP-medyje:");
        autoAibe.load("Duomenys\\ban.txt");
        Ks.ounn(autoAibe.toVisualizedString(""));
        Ks.oun("Išsiaiškinkite, kodėl medis augo tik į vieną pusę.");

        Ks.oun("");
        Ks.oun("Automobilių aibė AVL-medyje:");
        autoAibe3.load("Duomenys\\ban.txt");
        Ks.ounn(autoAibe3.toVisualizedString(""));

        SetADT<String> autoAibe4 = AutoApskaita.automobiliuMarkes(autoMasyvas);
        Ks.oun("Pasikartojančios automobilių markės:\n" + autoAibe4.toString());
    }
}
