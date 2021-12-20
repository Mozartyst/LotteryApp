package printers;

import entity.OneDraw;
import java.util.*;

public class Testy {
    private final ArrayList<OneDraw> lotteryNumber;
    private final Properties properties;

    public Testy(ArrayList<OneDraw> lotteryNumber, Properties properties) {
        this.properties = properties;
        this.lotteryNumber = lotteryNumber;
    }

    public void skutecznoscLiczbRandomowych(ArrayList<Integer> randomList) {
        int licznik = 0;
        int iloscTrafien = 0;
        int iloscPropozycji = 0;
        int trafionychLosowan = 0;
        boolean trafionyWeek = false;
        for (OneDraw week : lotteryNumber) {
            int traf = 0;
            iloscPropozycji += 1;
            for (Integer number : randomList) {
                if (week.getDrawNumbers().contains(number)) {
                    iloscTrafien += 1;
                    traf += 1;
                    trafionyWeek = true;
                }
            }

            if (trafionyWeek) {
                trafionychLosowan += 1;
                trafionyWeek = false;
            }
            System.out.print(traf + " ");
            licznik += 1;
            if (licznik % 50 == 0) System.out.println("");
        }
        System.out.println();
        System.out.println("Skutecznośc Randomowych " + "dla " + lotteryNumber.size() + " losowań:");
        System.out.println("-" + trafionychLosowan + " trafionych losowań" + "/ " + "skuteczność " + Math.round((float) trafionychLosowan / lotteryNumber.size() * 100) + " procent,");
        System.out.println("-" + iloscPropozycji + " propozycji");
        System.out.println("-" + iloscTrafien + " trafień");
    }
}

