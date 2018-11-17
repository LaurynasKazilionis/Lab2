package laborai.demo;

import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.SetADT;

public class AutoApskaita {

    public static SetADT<String> automobiliuMarkes(Sunkvežimis[] auto) {
        SetADT<Sunkvežimis> uni = new BstSetKTU<>(Sunkvežimis.pagalMarke);
        SetADT<String> kart = new BstSetKTU<>();
        for (Sunkvežimis a : auto) {
            int sizeBefore = uni.size();
            uni.add(a);

            if (sizeBefore == uni.size()) {
                kart.add(a.getMarkė());
            }
        }
        return kart;
    }
}
