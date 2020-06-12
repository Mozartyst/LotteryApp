package algorithm;

import dataSupport.FileService;
import entity.Number;
import lottoPropositions.NumbersAfterMultiCombinations;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Algorithm implements Serializable {
    //Trendy sprawdzić. wyszukać klucze podobne do siebie o jeden i zobaczyć ich trendy

    ArrayList<Integer> propositionList = new ArrayList<>();


    public ArrayList<Integer> getPropositionList(Integer index) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("FullLotteryNumbersFile");
        TreeMap<Integer, TreeMap<Integer, Boolean>> algorithm = FileService.loadObject("AlgorithmFile");
        TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbers).getProposition(index);
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject("ListOfNumbers");
        TreeMap<Integer,TreeMap<Integer,Integer>> test = FileService.loadObject("QuantityOfAppearedNumbers");
        ArrayList<Integer> tempPropositionList = new ArrayList<>();
        TreeMap<Integer, Integer> valueOfAppeared = new TreeMap<>();
        TreeMap<Integer, Integer> valueOfAppeared1 = new TreeMap<>();

        for (int i = 99; i >= 0; i--) {
            ArrayList<Integer> integerArrayList = lotteryNumbers.get(i);
            //Zrobić coś na wzór ML. Sprawdzać licznik wystąpień i sprawdzić czy rozkładają się równomiernie.
            integerArrayList.forEach((number) -> {
                if (valueOfAppeared.containsKey(number)) {
                    valueOfAppeared.replace(number, valueOfAppeared.get(number) + 1);
                } else {
                    valueOfAppeared.put(number, 1);
                }
            });
        }
        for (int i = 200; i >= 100; i--) {
            ArrayList<Integer> integerArrayList = lotteryNumbers.get(i);
            //Zrobić coś na wzór ML. Sprawdzać licznik wystąpień i sprawdzić czy rozkładają się równomiernie.
            /*
            * Najpierw przygotować wszystko na liście 2016-2019
            * Dokładajac do tej listy lecieć 2020 rok i sprawdzać
            *
            *
            *
            *
            * */
            integerArrayList.forEach((number) -> {
                if (valueOfAppeared1.containsKey(number)) {
                    valueOfAppeared1.replace(number, valueOfAppeared1.get(number) + 1);
                } else {
                    valueOfAppeared1.put(number, 1);
                }
            });
        }
        multiProposition.forEach((number, value) -> {
            if (algorithm.containsKey(number)) {
                algorithm.get(number).forEach((x, y) -> {
                    if (lotteryNumbers.get(index).contains(x)) {
                        tempPropositionList.add(number);
                    }
                });
            }
        });
        tempPropositionList.forEach((number) -> {
            if (multiProposition.get(number) > 1) {
                propositionList.add(number);
            }
        });
        return propositionList;
    }
}
