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
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadFile("LotteryNumbersFile");
        TreeMap<Integer, TreeMap<Integer, Boolean>> algorithm = (TreeMap<Integer, TreeMap<Integer, Boolean>>) FileService.loadObject("AlgorithmFile").getObject();
        TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbers).getProposition(index);
        TreeMap<Integer, Number> listOfNumbers = (TreeMap<Integer, Number>) FileService.loadObject("ListOfNumbers").getObject();
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
            Number number1 = listOfNumbers.get(number);
            if (number1.isHot()) {
                propositionList.add(number);
            }
        });
        return propositionList;
    }
}
