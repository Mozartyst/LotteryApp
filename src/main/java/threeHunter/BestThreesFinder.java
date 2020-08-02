package threeHunter;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationKeys;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class BestThreesFinder implements Runnable {
    private ArrayList<MultiCombinationKeys> multiCombinationKeys = new ArrayList<>();
    private TreeMap<CombinationNumbers, Integer> allThreesInLottery;
    private ArrayList<CombinationNumbers> listOfCombinations;
    private ArrayList<ArrayList<Integer>> lotteryNumbers;
    private int index;

    public BestThreesFinder(TreeMap<CombinationNumbers, Integer> allThreesInLottery, ArrayList<CombinationNumbers> listOfCombinations, ArrayList<ArrayList<Integer>> lotteryNumbers, int index) throws IOException, ClassNotFoundException {
        this.allThreesInLottery = allThreesInLottery;
        this.listOfCombinations = listOfCombinations;
        this.lotteryNumbers = lotteryNumbers;
        this.index = index;
    }

    @SneakyThrows
    @Override
    public void run() {
        ArrayList<CombinationNumbers> fullList = new ArrayList<>(allThreesInLottery.keySet());
        CombinationNumbers com1 = listOfCombinations.get(index);
        ArrayList<CombinationNumbers> combinationNumbers1 = new CombinationReducer()
                .returnListWithoutNumbers(fullList, com1.getFirstNumber(), com1.getSecondNumber(), com1.getThirdNumber());
        for (CombinationNumbers com2 : combinationNumbers1) {
            if (com2.compareTo(com1) > 0) {
                ArrayList<CombinationNumbers> combinationNumbers2 = new CombinationReducer()
                        .returnListWithoutNumbers(combinationNumbers1, com2.getFirstNumber(), com2.getSecondNumber(), com2.getThirdNumber());
                for (CombinationNumbers com3 : combinationNumbers2) {
                    if (com3.compareTo(com2) > 0) {
                            MultiCombinationKeys multiKeys = new MultiCombinationKeys(
                                    new CombinationNumbers(com1.getNumber()[0], com2.getNumber()[0], com3.getNumber()[0]),
                                    new CombinationNumbers(com1.getNumber()[1], com2.getNumber()[1], com3.getNumber()[1]),
                                    new CombinationNumbers(com1.getNumber()[2], com2.getNumber()[2], com3.getNumber()[2]));
                            int i = new ThreesChecker().howManyAppeared(multiKeys, lotteryNumbers);
                            if (i >= 40) {
                                multiCombinationKeys.add(multiKeys);
                                System.out.println(multiKeys + "= " + i);
                            }
                        }
                    }
                }
            }
        saveMulti();
    }

    private synchronized void saveMulti() throws IOException, ClassNotFoundException {
        ArrayList<MultiCombinationKeys> bestThrees = FileService.loadObject("IrishLottery/BestThrees");
        bestThrees.addAll(multiCombinationKeys);
        FileService.saveObject(bestThrees, "IrishLottery/BestThrees");
    }
}
