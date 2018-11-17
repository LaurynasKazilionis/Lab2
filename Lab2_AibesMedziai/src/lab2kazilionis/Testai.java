
package lab2kazilionis;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;
import laborai.studijosktu.AvlSetKTU;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.BstSetKTUx;
import laborai.studijosktu.Ks;
import laborai.studijosktu.SetADT;
import laborai.studijosktu.SortedSetADTx;

/**
 *
 * @author arvapu
 */
public class Testai
{
    static Sunkvežimis[] sunkvežimisBaze;
    static SortedSetADTx<Sunkvežimis> aSerija = new BstSetKTUx(new Sunkvežimis(), Sunkvežimis.pagalKaina);

    public static void main(String[] args) throws CloneNotSupportedException
    {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        aibėsTestas();
    }

    static SortedSetADTx generuotiAibe(int kiekis, int generN)
    {
        sunkvežimisBaze = new Sunkvežimis[generN];
        for (int i = 0; i < generN; i++)
        {
            sunkvežimisBaze[i] = new Sunkvežimis.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(sunkvežimisBaze));
        aSerija.clear();
        for (int i = 0; i < kiekis; i++)
        {
            aSerija.add(sunkvežimisBaze[i]);
        }
        return aSerija;
    }

    public static void aibėsTestas() throws CloneNotSupportedException
    {
        Sunkvežimis a1 = new Sunkvežimis("Renault", "Tranziter", 1997, 50000, 1700);
        Sunkvežimis a2 = new Sunkvežimis.Builder()
                .markė("Renault")
                .modelis("Mugane")
                .gamMetai(2001)
                .rida(20000)
                .kaina(3500)
                .build();
        Sunkvežimis a3 = new Sunkvežimis.Builder().buildRandom();
        Sunkvežimis a4 = new Sunkvežimis("Renault Tranziter 2001 115900 700");
        Sunkvežimis a5 = new Sunkvežimis("Renault Mugane 1946 365100 9500");
        Sunkvežimis a6 = new Sunkvežimis("Ryja   CYvan  2001  36400 80.3");
        Sunkvežimis a7 = new Sunkvežimis("Renault Cruz 2001 115900 7500");
        Sunkvežimis a8 = new Sunkvežimis("Renault Mugane 1946 365100 950");
        Sunkvežimis a9 = new Sunkvežimis("Honda   Truckerio  2007  36400 850.3");

        Sunkvežimis[] sunkvežimisMasyvas =
        {
            a9, a7, a8, a5, a1, a6
        };

        Ks.oun("Truck Aibė:");
        SortedSetADTx<Sunkvežimis> sunkvežimiuAibe = new BstSetKTUx(new Sunkvežimis());

        for (Sunkvežimis a : sunkvežimisMasyvas)
        {
            sunkvežimiuAibe.add(a);
            Ks.oun("Aibė papildoma: " + a + ". Jos dydis: " + sunkvežimiuAibe.size());
        }
        Ks.oun("");
        Ks.oun(sunkvežimiuAibe.toVisualizedString(""));

        SortedSetADTx<Sunkvežimis> sunkvežimiuAibeKopija
                = (SortedSetADTx<Sunkvežimis>) sunkvežimiuAibe.clone();

        sunkvežimiuAibeKopija.add(a2);
        sunkvežimiuAibeKopija.add(a3);
        sunkvežimiuAibeKopija.add(a4);
        Ks.oun("Papildyta sunkvežimų aibės kopija:");
        Ks.oun(sunkvežimiuAibeKopija.toVisualizedString(""));

        a9.setRida(10000);

        Ks.oun("Originalas:");
        Ks.ounn(sunkvežimiuAibe.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibėje?");
        for (Sunkvežimis a : sunkvežimisMasyvas)
        {
            Ks.oun(a + ": " + sunkvežimiuAibe.contains(a));
        }
        Ks.oun(a2 + ": " + sunkvežimiuAibe.contains(a2));
        Ks.oun(a3 + ": " + sunkvežimiuAibe.contains(a3));
        Ks.oun(a4 + ": " + sunkvežimiuAibe.contains(a4));
        Ks.oun("");

        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
        for (Sunkvežimis a : sunkvežimisMasyvas)
        {
            Ks.oun(a + ": " + sunkvežimiuAibeKopija.contains(a));
        }
        Ks.oun(a2 + ": " + sunkvežimiuAibeKopija.contains(a2));
        Ks.oun(a3 + ": " + sunkvežimiuAibeKopija.contains(a3));
        Ks.oun(a4 + ": " + sunkvežimiuAibeKopija.contains(a4));
        Ks.oun("");

        Ks.oun("Elementų šalinimas iš kopijos. Aibės dydis prieš šalinimą:  " + sunkvežimiuAibeKopija.size());
        for (Sunkvežimis a : new Sunkvežimis[]
        {
            a2, a1, a9, a8, a5, a3, a4, a2, a7, a6, a7, a9
        })
        {
            sunkvežimiuAibeKopija.remove(a);
            Ks.oun("Iš truckaibes kopijos pašalinama: " + a + ". Jos dydis: " + sunkvežimiuAibeKopija.size());
        }
        Ks.oun("");

        Ks.oun("Sunkvežimų aibė su iteratoriumi:");
        Ks.oun("");
        for (Sunkvežimis a : sunkvežimiuAibe)
        {
            Ks.oun(a);
        }
        Ks.oun("");
        Ks.oun("Sunkvežimų aibė AVL-medyje:");
        SortedSetADTx<Sunkvežimis> sunkvežimiuAibe3 = new AvlSetKTUx(new Sunkvežimis());
        for (Sunkvežimis a : sunkvežimisMasyvas)
        {
            sunkvežimiuAibe3.add(a);
        }
        Ks.ounn(sunkvežimiuAibe3.toVisualizedString(""));

        Ks.oun("Sunkvežimų aibė su iteratoriumi:");
        Ks.oun("");
        for (Sunkvežimis a : sunkvežimiuAibe3)
        {
            Ks.oun(a);
        }

        Ks.oun("");
        Ks.oun("Sunkvežimų aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator iter = sunkvežimiuAibe3.descendingIterator();
        while (iter.hasNext())
        {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Sunkvežimų aibės toString() metodas:");
        Ks.ounn(sunkvežimiuAibe3);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        sunkvežimiuAibe3.clear();
        sunkvežimiuAibe3.clear();

        Ks.oun("");
        Ks.oun("Sunkvežimų aibė DP-medyje:");
        sunkvežimiuAibe3.load("Duomenys\\ban.txt");
        Ks.ounn(sunkvežimiuAibe3.toVisualizedString(""));
        // Ks.oun("Išsiaiškinkite, kodėl medis augo tik į vieną pusę.");

        Ks.oun("");
        Ks.oun("Automobilių aibė AVL-medyje:");
        sunkvežimiuAibe3.load("Duomenys\\ban.txt");
        Ks.ounn(sunkvežimiuAibe3.toVisualizedString(""));

        SetADT<String> autoAibe4 = SunkvežimioApskaita.automobiliuMarkes(sunkvežimisMasyvas);
        Ks.oun("Pasikartojančios automobilių markės:\n" + autoAibe4.toString());
        
        
        BstSetKTU <Integer> set = new BstSetKTU<>();
      
          set.add(100);
          set.add(50);
          set.add(30);
          set.add(15);
          set.add(200);
          set.add(300);
          set.add(350);
          set.add(41);
          set.add(39);
          set.add(43);

          

        Ks.oun("");
        Ks.ounn(set.toVisualizedString(""));
        

        
        
    }
}
