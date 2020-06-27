package algorithm;

import dataSupport.FileService;
import entity.Number;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Algorithm implements Serializable {

    private final ArrayList<Integer> propositionList = new ArrayList<>();
    private final TreeMap<Integer, ArrayList<Double>> results = new TreeMap<>();


    public void getPropositionList(Integer index) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LastYearLotteryNumbersFile");
        TreeMap<Integer, TreeMap<Integer, Boolean>> algorithm = FileService.loadObject("AlgorithmFile");
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject("ListOfNumbers");
        TreeMap<Integer, TreeMap<Integer, Integer>> test = FileService.loadObject("QuantityOfAppearedNumbers");
        Double counter = 100.0;

    }
}
