package laborai.demo;

import java.util.HashSet;
import java.util.Random;
import laborai.studijosktu.BstSetKTUx2;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.SortedSetADTx;
import laborai.studijosktu.BstSetKTUx;
import laborai.gui.MyException;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class GreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finish";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("laborai.gui.messages");

    private static final String[] TYRIMU_VARDAI = {"addBstRec", "addBstIte", "addAvlRec", "removeBst"};
    private static final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;
    private final String[] errors;

    private final SortedSetADTx<Sunkvežimis> aSeries = new BstSetKTUx(new Sunkvežimis(), Sunkvežimis.pagalKaina);
    private final SortedSetADTx<Sunkvežimis> aSeries2 = new BstSetKTUx2(new Sunkvežimis());
    private final SortedSetADTx<Sunkvežimis> aSeries3 = new AvlSetKTUx(new Sunkvežimis());

    public GreitaveikosTyrimas() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
        errors = new String[]{
            MESSAGES.getString("error1"),
            MESSAGES.getString("error2"),
            MESSAGES.getString("error3"),
            MESSAGES.getString("error4")
        };
    }

    public void pradetiTyrima() {
        try {
            SisteminisTyrimas();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void SisteminisTyrimas() throws InterruptedException {
        try {
            for (int k : TIRIAMI_KIEKIAI) {
                Sunkvežimis[] autoMas = SunkvežimiuGamyba.generuotiIrIsmaisyti(k, 1.0);
                aSeries.clear();
                aSeries2.clear();
                aSeries3.clear();
                tk.startAfterPause();
                tk.start();
                for (Sunkvežimis a : autoMas) {
                    aSeries.add(a);
                }
                tk.finish(TYRIMU_VARDAI[0]);
                for (Sunkvežimis a : autoMas) {
                    aSeries2.add(a);
                }
                tk.finish(TYRIMU_VARDAI[1]);
                for (Sunkvežimis a : autoMas) {
                    aSeries3.add(a);
                }
                tk.finish(TYRIMU_VARDAI[2]);
                for (Sunkvežimis a : autoMas) {
                    aSeries.remove(a);
                }
                tk.finish(TYRIMU_VARDAI[3]);
                greitaveika1(k);
                greitaveika2(k);
                tk.seriesFinish();
            }
            tk.logResult(FINISH_COMMAND);
        } catch (MyException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                tk.logResult(errors[e.getCode()] + ": " + e.getMessage());
            } else if (e.getCode() == 4) {
                tk.logResult(MESSAGES.getString("msg3"));
            } else {
                tk.logResult(e.getMessage());
            }
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    
    
    public void greitaveika1(int kiekis) throws InterruptedException
    {
        TreeSet<Integer> A = new TreeSet<>();
        HashSet<Integer> B = new HashSet<>();

        for (int i = 0; i < kiekis; i++)
        {
            A.add(i);
            B.add(i);
        }

        int nr = new Random().nextInt(kiekis);
        System.out.println("\nTreeSet<Integer> testavimas");
        long t0 = System.nanoTime();
        A.add(nr);
        long t1 = System.nanoTime();
        long elapsedTime = t1 - t0;
        double seconds = (double)elapsedTime / 1_000_00.0;
        System.out.println("Add metodas, pridedant " + nr + " iš " + kiekis + " buvo atliktas per " + seconds);
        tk.finish("removeTSet");


        System.out.println("HashSet<Integer> testavimas");   
        t0 = System.nanoTime();
        B.add(nr);
        t1 = System.nanoTime();
        elapsedTime = t1 - t0;
        seconds = (double)elapsedTime / 1_000_00.0;
        System.out.println("Remove metodas, šalinant " + nr + " iš " + kiekis + " buvo atliktas per " + seconds);
        System.out.println("------------------------------------------------------------------------------------");
        tk.finish("removeHSet");
        
    }
    
    
    
    
 public void greitaveika2(int kiekis) throws InterruptedException
   {
        TreeSet<Integer> A = new TreeSet<>();
        HashSet<Integer> B = new HashSet<>();

        for (int i = 0; i < kiekis; i++)
        {
            A.add(i);
            B.add(i);
        }

        int nr = new Random().nextInt(kiekis);
        System.out.println("\nTreeSet<Integer> testavimas");
        long t0 = System.nanoTime();
        A.contains(nr);
        long t1 = System.nanoTime();
        long elapsedTime = t1 - t0;
        double seconds = (double)elapsedTime / 1_000_00.0;
        System.out.println("Contains metodas, ieskant " + nr + " tarp " + kiekis + " buvo atliktas per " + seconds);
        tk.finish("containTSet");

        System.out.println("HashSet<Integer> testavimas");   
        t0 = System.nanoTime();
        B.contains(nr);
        t1 = System.nanoTime();
        elapsedTime = t1 - t0;
        seconds = (double)elapsedTime / 1_000_00.0;
        System.out.println("Contains metodas, ieskant " + nr + " tarp " + kiekis + " buvo atliktas per " + seconds);
        System.out.println("------------------------------------------------------------------------------------");
        tk.finish("containHSet");
    }
 
 
 
}
