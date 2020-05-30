package algorithm;

import dataSupport.FileService;
import entity.Number;
import lottoPropositions.NumbersAfterMultiCombinations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Algorithm implements Serializable {
    //Trendy sprawdzić. wyszukać klucze podobne do siebie o jeden i zobaczyć ich trendy

    ArrayList<Integer> propositionList = new ArrayList<>();


    public ArrayList<Integer> getPropositionList(Integer index) {
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LastYearLotteryNumbersFile");
        TreeMap<Integer, TreeMap<Integer, Boolean>> algorithm = FileService.loadObject("AlgorithmFile");
        TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbers).getProposition(index);
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject("ListOfNumbers");
        ArrayList<Integer> tempPropositionList = new ArrayList<>();
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
            if (multiProposition.get(number)>1) {
                propositionList.add(number);
            }
        });
        return propositionList;
    }
}
