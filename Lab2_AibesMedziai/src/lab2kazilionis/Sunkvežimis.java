/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2kazilionis;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import laborai.studijosktu.KTUable;
import laborai.studijosktu.Ks;

/**
 *
 * @author arvapu
 */
public class Sunkvežimis implements KTUable<Sunkvežimis>
{
    private static final int priimtinųMetųRiba = 1990;
    private static final int esamiMetai = LocalDate.now().getYear();
    private static final double minKaina = 100.0;
    private static final double maxKaina = 333000.0;
    private static final String idCode = "TA";   //  ***** nauja
    private static int serNr = 100;               //  ***** nauja
    private final String truckRegNr;
    private String markė = "";
    private String modelis = "";
    private int gamMetai = -1;
    private int rida = -1;
    private double kaina = -1.0;

    public Sunkvežimis()
    {
        truckRegNr = idCode + (serNr++);
    }
    
    public Sunkvežimis(String markė, String modelis,int gamMetai, int rida, double kaina)
    {
        truckRegNr = idCode + (serNr++);    // suteikiamas originalus autoRegNr
        this.markė = markė;
        this.modelis = modelis;
        this.gamMetai = gamMetai;
        this.rida = rida;
        this.kaina = kaina;
        validate();
    }

    public Sunkvežimis(String dataString)
    {
        truckRegNr = idCode + (serNr++);   
        this.parse(dataString);
    }

    public Sunkvežimis(Builder builder) 
    { 
        truckRegNr = idCode + (serNr++);    // suteikiamas originalus autoRegNr
        this.markė = builder.markė;
        this.modelis = builder.modelis;
        this.gamMetai = builder.gamMetai;
        this.rida = builder.rida;
        this.kaina = builder.kaina;
        validate();
    }
    
    
    @Override
    public Sunkvežimis create(String dataString)
    {
        return new Sunkvežimis(dataString);
    }
    
    @Override
    public String validate()
    {
        String klaidosTipas = "";
        if (gamMetai < priimtinųMetųRiba || gamMetai > esamiMetai)
        {
            klaidosTipas = "Netinkami gamybos metai, turi būti ["
                    + priimtinųMetųRiba + ":" + esamiMetai + "]";
        }
        if (kaina < minKaina || kaina > maxKaina)
        {
            klaidosTipas += " Kaina už leistinų ribų [" + minKaina
                    + ":" + maxKaina + "]";
        }
        return klaidosTipas;
    } 
    
    @Override
    public void parse(String dataString)
    {
        try {   
            // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(dataString);
            markė = ed.next();
            modelis = ed.next();
            gamMetai = ed.nextInt();
            setRida(ed.nextInt());
            setKaina(ed.nextDouble());
            validate();
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie sunkvežimį -> " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie sunkvežimį -> " + dataString);
        }
    }

    @Override
    public String toString()
    {
        return getTruckRegNr() + "=" + markė + "_" + modelis + ":" + gamMetai + " " + getRida() + " " + String.format("%4.1f", kaina);
    }

    // Getters
    public String getTruckRegNr()
    {
        return truckRegNr;
    }

    public String getMarkė()
    {
        return markė;
    }

    public String getModelis()
    {
        return modelis;
    }

    public int getGamMetai()
    {
        return gamMetai;
    }

    public int getRida()
    {
        return rida;
    }

    public double getKaina()
    {
        return kaina;
    }
    
    // Setters
    public void setRida(int rida)
    {
        this.rida = rida;
    }

    public void setKaina(double kaina)
    {
        this.kaina = kaina;
    }
    
    @Override
    public int compareTo(Sunkvežimis other)
    {
        return getTruckRegNr().compareTo(other.getTruckRegNr());
    }
    
    public static Comparator<Sunkvežimis> pagalMarke = (Sunkvežimis t1, Sunkvežimis t2) -> t1.markė.compareTo(t2.markė); // pradžioje pagal markes, o po to pagal modelius

    public static Comparator<Sunkvežimis> pagalKaina = (Sunkvežimis t1, Sunkvežimis t2) -> {
        // didėjanti tvarka, pradedant nuo mažiausios
        if (t1.kaina < t2.kaina) {
            return -1;
        }
        if (t1.kaina > t2.kaina) {
            return +1;
        }
        return 0;
    };

    public static Comparator<Sunkvežimis> pagalMetusKainą = (Sunkvežimis t1, Sunkvežimis t2) -> {
        // metai mažėjančia tvarka, esant vienodiems lyginama kaina
        if (t1.gamMetai > t2.gamMetai) {
            return +1;
        }
        if (t1.gamMetai < t2.gamMetai) {
            return -1;
        }
        if (t1.kaina > t2.kaina) {
            return +1;
        }
        if (t1.kaina < t2.kaina) {
            return -1;
        }
        return 0;
    };
    // Sunkvežimis klases objektų gamintojas
    public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Atsitiktinių generatorius
        private final static String[][] MODELIAI = 
        { 
            // galimų sunkvezimiu markių ir jų modelių masyvas
            {"Scania", "R164", "R470", "R480LA", "R420"},
            {"Ford", "F-Max", "1848T", "1812", "Cargo", "2533HR"},
            {"Volvo", "FL280", "FL10"},
            {"Honda", "Accorder", "Luxi", "Fazz"},
            {"Renault", "280DXI", "Kerax", "Midium", "Arah"},
            {"Mercedes-Benz", "1228", "1824", "Atego"}
        };

        private String markė = "";
        private String modelis = "";
        private int gamMetai = -1;
        private int rida = -1;
        private double kaina = -1.0;

        public Sunkvežimis build()
        {
            return new Sunkvežimis(this);
        }

        public Sunkvežimis buildRandom()
        {
            int ma = RANDOM.nextInt(MODELIAI.length);        
            int mo = RANDOM.nextInt(MODELIAI[ma].length - 1) + 1;              
            return new Sunkvežimis (MODELIAI[ma][0],
                    MODELIAI[ma][mo],
                    2000 + RANDOM.nextInt(20),// metai tarp 2000 ir 2009
                    6000 + RANDOM.nextInt(222000),// rida tarp 6000 ir 228000
                    800 + RANDOM.nextDouble() * 88000);// kaina tarp 800 ir 88800
        }

        public Builder gamMetai(int gamMetai) 
        {
            this.gamMetai = gamMetai;
            return this;
        }

        public Builder markė(String markė) 
        {
            this.markė = markė;
            return this;
        }

        public Builder modelis(String modelis)
        {
            this.modelis = modelis;
            return this;
        }

        public Builder rida(int rida)
        {
            this.rida = rida;
            return this;
        }

        public Builder kaina(double kaina)
        {
            this.kaina = kaina;
            return this;
        }
    }
    
}
