package testy;

import algorithm.Algorithm;
import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.Number;
import entity.OneDraw;
import lottoPropositions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

public class Testy {
    private final NumbersAfterDuet duet = new NumbersAfterDuet();
    private final NumbersAfterSlant slant = new NumbersAfterSlant();
    private final ArrayList<OneDraw> lotteryNumber;
    private final Properties properties;

    public Testy(ArrayList<OneDraw> lotteryNumber, Properties properties) {
        this.properties = properties;
        this.lotteryNumber = lotteryNumber;
    }

    public void skutecznoscDuet() {
        int iloscTrafien = 0;
        int iloscPropozycji = 0;
        for (int i = (lotteryNumber.size()) - 2; i > 0; i--) {
            ArrayList<Integer> propositionNumbers = duet.returnNumbersAfterDuet(lotteryNumber, i);
            ArrayList<Integer> listOfNumbers = lotteryNumber.get(i + 1).getDrawNumbers();
            for (Integer number : propositionNumbers) {
                if (listOfNumbers.contains(number)) {
                    iloscTrafien += 1;
                }

            }
            if (propositionNumbers.size() != 0) {
                iloscPropozycji += 1;
            }
        }
        System.out.println("Skutecznośc DUET " + iloscTrafien + " trafień" + " / " + iloscPropozycji + " propozycji" + " / " + lotteryNumber.size() + " losowań" + " / " + Math.round((float) iloscTrafien / iloscPropozycji * 100) + " procent");

    }

    public void skutecznoscSlant() {
        int iloscTrafien = 0;
        int iloscPropozycji = 0;
        for (int i = (lotteryNumber.size()) - 2; i > 1; i--) {
            ArrayList<Integer> propositionNumbers = slant.returnNextSlantNumber(lotteryNumber, i);
            ArrayList<Integer> listOfNumbers = lotteryNumber.get(i + 1).getDrawNumbers();
            for (Integer number : propositionNumbers) {
                if (listOfNumbers.contains(number)) {
                    iloscTrafien += 1;
                }
            }
            if (propositionNumbers.size() != 0) {
                iloscPropozycji += 1;
            }
        }
        System.out.println("Skutecznośc SLANT " + iloscTrafien + " trafień" + " / " + iloscPropozycji + " propozycji" + " / " + lotteryNumber.size() + " losowań " + " / " + Math.round((float) iloscTrafien / iloscPropozycji * 100) + " procent");
    }

    public void skutecznoscNumbersAfterNumber() throws IOException, ClassNotFoundException {
        TreeMap<Integer, TreeMap<Integer, Integer>> listOfNumbersAfterNumbers = FileService.loadObject(properties.getProperty("afterNumber"));
        ArrayList<Integer> listaTraf = new ArrayList<>();
        int iloscTrafien = 0;
        int iloscPropozycji = 0;
        int trafionychLosowan = 0;
        int iloscLiczbtypowanych = 0;
        boolean trafionyWeek = false;
        NumbersAfterNumber numbersAfterNumber = new NumbersAfterNumber(listOfNumbersAfterNumbers, lotteryNumber);
        for (int i = (lotteryNumber.size() - 2); i > 0; i--) {
            int traf = 0;
            TreeMap<Integer, Integer> propositionNumbers = numbersAfterNumber.getPropositionNumbersAfterNumber(i);
            if (propositionNumbers.size() != 0) {
                iloscLiczbtypowanych += propositionNumbers.size();
                iloscPropozycji += 1;
                ArrayList<Integer> listOfNumbersNext = lotteryNumber.get(i + 1).getDrawNumbers();
                for (Integer number : listOfNumbersNext) {
                    if (propositionNumbers.containsKey(number)) {
                        traf += 1;
                        iloscTrafien += 1;
                        trafionyWeek = true;
                    }
                }
            }
            if (trafionyWeek) {
                trafionychLosowan += 1;
                trafionyWeek = false;
            }
            listaTraf.add(traf);
        }
        System.out.println(listaTraf);
        System.out.println("Skutecznośc NumAftNum " + "dla " + lotteryNumber.size() + " losowań:");
        System.out.println("-" + trafionychLosowan + " trafionych losowań" + "/ " + "skuteczność " + Math.round((float) trafionychLosowan / lotteryNumber.size() * 100) + " procent,");
        System.out.println("-" + iloscPropozycji + " propozycji");
        System.out.println("-" + iloscTrafien + " trafień");
        System.out.println("-" + iloscLiczbtypowanych + " ilość liczb typowanych" + " / " + iloscLiczbtypowanych / lotteryNumber.size() + " średnio na losowanie");
    }

    public void skutecznoscNumbersAfterPairs() throws IOException, ClassNotFoundException {
        TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersAfterPairs = FileService.loadObject(properties.getProperty("afterPairs"));
        ArrayList<Integer> listaTraf = new ArrayList<>();
        int iloscTrafien = 0;
        int iloscPropozycji = 0;
        int trafionychLosowan = 0;
        int iloscLiczbtypowanych = 0;
        boolean trafionyWeek = false;
        NumbersAfterPairs numbersAfterPairs = new NumbersAfterPairs(lotteryNumber, listOfNumbersAfterPairs);
        for (int i = (lotteryNumber.size() - 2); i > 0; i--) {
            int traf = 0;
            TreeMap<Integer, Integer> propositionNumbers = numbersAfterPairs.getPropositionNumbersAfterPairs(i);
            if (propositionNumbers.size() != 0) {
                iloscLiczbtypowanych += propositionNumbers.size();
                iloscPropozycji += 1;
                ArrayList<Integer> listOfNumbersNext = lotteryNumber.get(i + 1).getDrawNumbers();
                for (Integer number : listOfNumbersNext) {
                    if (propositionNumbers.containsKey(number)) {
                        iloscTrafien += 1;
                        traf += 1;
                        trafionyWeek = true;
                    }
                }
            }
            if (trafionyWeek) {
                trafionychLosowan += 1;
                trafionyWeek = false;
            }
            listaTraf.add(traf);
        }
        System.out.println(listaTraf);
        System.out.println("Skutecznośc NumAftPairs " + "dla " + lotteryNumber.size() + " losowań:");
        System.out.println("-" + trafionychLosowan + " trafionych losowań" + "/ " + "skuteczność " + Math.round((float) trafionychLosowan / lotteryNumber.size() * 100) + " procent,");
        System.out.println("-" + iloscPropozycji + " propozycji");
        System.out.println("-" + iloscTrafien + " trafień");
        System.out.println("-" + iloscLiczbtypowanych + " ilość liczb typowanych" + " / " + iloscLiczbtypowanych / lotteryNumber.size() + " średnio na losowanie");
    }

    public void skutecznoscNumbersAfterTriple() throws IOException, ClassNotFoundException {
        TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersAfterTriple = FileService.loadObject(properties.getProperty("afterTriple"));
        ArrayList<Integer> listaTraf = new ArrayList<>();
        int iloscTrafien = 0;
        int iloscPropozycji = 0;
        int trafionychLosowan = 0;
        int iloscLiczbtypowanych = 0;
        boolean trafionyWeek = false;
        for (OneDraw week : lotteryNumber) {
            int i = lotteryNumber.indexOf(week);
            if (i == 0) {
                continue;
            }
            int traf = 0;
            TreeMap<Integer, Integer> propositionNumbers = new NumbersAfterTriple(lotteryNumber, listOfNumbersAfterTriple).getPropositionNumbersAfterTriple(i);
            if (propositionNumbers.size() != 0) {
                iloscLiczbtypowanych += propositionNumbers.size();
                iloscPropozycji += 1;
                ArrayList<Integer> listOfNumbersNext = lotteryNumber.get(i - 1).getDrawNumbers();
                for (Integer number : listOfNumbersNext) {
                    if (propositionNumbers.containsKey(number)) {
                        iloscTrafien += 1;
                        traf += 1;
                        trafionyWeek = true;
                    }
                }
            }
            if (trafionyWeek) {
                trafionychLosowan += 1;
                trafionyWeek = false;
            }
            listaTraf.add(traf);
        }
        System.out.println(listaTraf);
        System.out.println("Skutecznośc NumAftTriple " + "dla " + lotteryNumber.size() + " losowań:");
        System.out.println("-" + trafionychLosowan + " trafionych losowań" + "/ " + "skuteczność " + Math.round((float) trafionychLosowan / lotteryNumber.size() * 100) + " procent,");
        System.out.println("-" + iloscPropozycji + " propozycji");
        System.out.println("-" + iloscTrafien + " trafień");
        System.out.println("-" + iloscLiczbtypowanych + " ilość liczb typowanych" + " / " + iloscLiczbtypowanych / lotteryNumber.size() + " średnio na losowanie");
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

    public void skutecznoscMultiCombination() throws IOException, ClassNotFoundException {
        ArrayList<Integer> listaTraf = new ArrayList<>();
        int iloscTrafien = 0;
        int iloscPropozycji = 0;
        int trafionychLosowan = 0;
        int iloscLiczbtypowanych = 0;
        boolean trafionyWeek = false;
        for (int i = (lotteryNumber.size() - 4); i > 0; i--) {
            int traf = 0;
            TreeMap<Integer, Integer> propositionNumbers = new NumbersAfterMultiCombinations(lotteryNumber)
                    .getProposition(i,properties);
            if (propositionNumbers.size() != 0) {
                iloscLiczbtypowanych += propositionNumbers.size();
                iloscPropozycji += 1;
                ArrayList<Integer> listOfNumbersNext = lotteryNumber.get(i - 1).getDrawNumbers();
                for (Integer number : listOfNumbersNext) {
                    if (propositionNumbers.containsKey(number)) {
                        iloscTrafien += 1;
                        traf += 1;
                        trafionyWeek = true;
                    }
                }
            }
            if (trafionyWeek) {
                trafionychLosowan += 1;
                trafionyWeek = false;
            }
            listaTraf.add(traf);
        }
        System.out.println(listaTraf);
        System.out.println("Skutecznośc NAMultiBombination " + "dla " + (lotteryNumber.size()-4) + " losowań:");
        System.out.println("-" + trafionychLosowan + " trafionych losowań" + "/ " + "skuteczność " + Math.round((float) trafionychLosowan / lotteryNumber.size() * 100) + " procent,");
        System.out.println("-" + iloscPropozycji + " propozycji");
        System.out.println("-" + iloscTrafien + " trafień");
        System.out.println("-" + iloscLiczbtypowanych + " ilość liczb typowanych" + " / " + iloscLiczbtypowanych / (lotteryNumber.size()-4) + " średnio na losowanie");
    }
    public void skutecznoscAlgorithm() throws IOException, ClassNotFoundException {
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        ArrayList<Integer> listaTraf = new ArrayList<>();
        int iloscTrafien = 0;
        int iloscPropozycji = 0;
        int trafionychLosowan = 0;
        int iloscLiczbtypowanych = 0;
        boolean trafionyWeek = false;
        for (int i = (lotteryNumber.size() - 4); i > 0; i--) {
            int traf = 0;
            TreeSet<Integer> propositionNumbers = new Algorithm().getPropositionList(i, properties, lotteryNumber,listOfNumbers);
            if (propositionNumbers.size() != 0) {
                iloscLiczbtypowanych += propositionNumbers.size();
                iloscPropozycji += 1;
                ArrayList<Integer> listOfNumbersNext = lotteryNumber.get(i - 1).getDrawNumbers();
                for (Integer o : listOfNumbersNext) {
                    if (propositionNumbers.contains(o)) {
                        iloscTrafien += 1;
                        traf += 1;
                        trafionyWeek = true;
                    }
                }
            }
            if (trafionyWeek) {
                trafionychLosowan += 1;
                trafionyWeek = false;
            }
            listaTraf.add(traf);
        }
        System.out.println(listaTraf);
        System.out.println("Skutecznośc Algorithm " + "dla " + (lotteryNumber.size()-4) + " losowań:");
        System.out.println("-" + trafionychLosowan + " trafionych losowań" + "/ " + "skuteczność " + Math.round((float) trafionychLosowan / (lotteryNumber.size()-4) * 100) + " procent,");
        System.out.println("-" + iloscPropozycji + " propozycji");
        System.out.println("-" + iloscTrafien + " trafień");
        System.out.println("-" + iloscLiczbtypowanych + " ilość liczb typowanych" + " / " + iloscLiczbtypowanych / (lotteryNumber.size()-4) + " średnio na losowanie");
    }
}

