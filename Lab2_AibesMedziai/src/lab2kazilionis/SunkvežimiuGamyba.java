/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2kazilionis;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import laborai.gui.MyException;

/**
 *
 * @author arvapu
 */
public class SunkvežimiuGamyba
{
    private static Sunkvežimis[] sunkvezimiai;
    private static int pradinisIndeksas = 0, galinisIndeksas = 0;
    private static boolean arPradzia = true;

    public static Sunkvežimis[] generuoti(int kiekis)
    {
        sunkvezimiai = new Sunkvežimis[kiekis];
        for (int i = 0; i < kiekis; i++) {
            sunkvezimiai[i] = new Sunkvežimis.Builder().buildRandom();
        }
        return sunkvezimiai;
    }

    public static Sunkvežimis[] generuotiIrIsmaisyti(int aibesDydis,
            double isbarstymoKoeficientas) throws MyException
    {
        return generuotiIrIsmaisyti(aibesDydis, aibesDydis, isbarstymoKoeficientas);
    }

    /**
     *
     * @param aibesDydis
     * @param aibesImtis
     * @param isbarstymoKoeficientas
     * @return Gražinamas aibesImtis ilgio masyvas
     * @throws MyException
     */
    public static Sunkvežimis[] generuotiIrIsmaisyti(int aibesDydis,
            int aibesImtis, double isbarstymoKoeficientas) throws MyException
    {
        sunkvezimiai = generuoti(aibesDydis);
        return ismaisyti(sunkvezimiai, aibesImtis, isbarstymoKoeficientas);
    }

    // Galima paduoti masyvą išmaišymui iš išorės
    public static Sunkvežimis[] ismaisyti(Sunkvežimis[] sunkvežimisBaze,
            int kiekis, double isbarstKoef) throws MyException
    {
        if (sunkvežimisBaze == null)
        {
            throw new IllegalArgumentException("TruckBaze yra null");
        }
        if (kiekis <= 0)
        {
            throw new MyException(String.valueOf(kiekis), 1);
        }
        if (sunkvežimisBaze.length < kiekis)
        {
            throw new MyException(sunkvežimisBaze.length + " >= " + kiekis, 2);
        }
        if ((isbarstKoef < 0) || (isbarstKoef > 1)) 
        {
            throw new MyException(String.valueOf(isbarstKoef), 3);
        }

        int likusiuKiekis = sunkvežimisBaze.length - kiekis;
        int pradziosIndeksas = (int) (likusiuKiekis * isbarstKoef / 2);

        Sunkvežimis[] pradineAutomobiliuImtis = Arrays.copyOfRange(sunkvežimisBaze, pradziosIndeksas, pradziosIndeksas + kiekis);
        Sunkvežimis[] likusiAutomobiliuImtis = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(sunkvežimisBaze, 0, pradziosIndeksas)),
                        Arrays.stream(Arrays.copyOfRange(sunkvežimisBaze, pradziosIndeksas + kiekis, sunkvežimisBaze.length)))
                .toArray(Sunkvežimis[]::new);

        Collections.shuffle(Arrays.asList(pradineAutomobiliuImtis)
                .subList(0, (int) (pradineAutomobiliuImtis.length * isbarstKoef)));
        Collections.shuffle(Arrays.asList(likusiAutomobiliuImtis)
                .subList(0, (int) (likusiAutomobiliuImtis.length * isbarstKoef)));

        SunkvežimiuGamyba.pradinisIndeksas = 0;
        galinisIndeksas = likusiAutomobiliuImtis.length - 1;
        SunkvežimiuGamyba.sunkvezimiai = likusiAutomobiliuImtis;
        return pradineAutomobiliuImtis;
    }

    public static Sunkvežimis gautiIsBazes() throws MyException 
    {
        if ((galinisIndeksas - pradinisIndeksas) < 0) 
        {
            throw new MyException(String.valueOf(galinisIndeksas - pradinisIndeksas), 4);
        }
        // Vieną kartą Sunkvezimis imamas iš masyvo pradžios, kitą kartą - iš galo.     
        arPradzia = !arPradzia;
        return sunkvezimiai[arPradzia ? pradinisIndeksas++ : galinisIndeksas--];
    }
}
