/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2kazilionis;

import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.SetADT;

/**
 *
 * @author arvapu
 */
public class SunkvežimioApskaita
{
    public static SetADT<String> automobiliuMarkes(Sunkvežimis[] sunkvežimis)
    {
        SetADT<Sunkvežimis> uni = new BstSetKTU<>(Sunkvežimis.pagalMarke);
        SetADT<String> kart = new BstSetKTU<>();
        for (Sunkvežimis a : sunkvežimis) 
        {
            int sizeBefore = uni.size();
            uni.add(a);

            if (sizeBefore == uni.size())
            {
                kart.add(a.getMarkė());
            }
        }
        return kart;
    }
}
