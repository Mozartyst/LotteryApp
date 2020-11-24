package threeHunter;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class BestThreesFinder implements Runnable {
    private final ArrayList<MultiCombinationNumber> multiCombinationKeys = new ArrayList<>();
    private final TreeMap<CombinationNumbers, Integer> allThreesInLottery;
    private final ArrayList<CombinationNumbers> listOfCombinations;
    private final ArrayList<OneDraw> lotteryNumbers;
    private final int index;

    public BestThreesFinder(TreeMap<CombinationNumbers, Integer> allThreesInLottery, ArrayList<CombinationNumbers> listOfCombinations, ArrayList<OneDraw> lotteryNumbers, int index){
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
                            MultiCombinationNumber multiKeys = new MultiCombinationNumber(
                                    new int[]{com1.getNumbers()[0], com2.getNumbers()[0], com3.getNumbers()[0]}
                                    ,new int[]{com1.getNumbers()[1], com2.getNumbers()[1], com3.getNumbers()[1]}
                                    ,new int[]{com1.getNumbers()[2], com2.getNumbers()[2], com3.getNumbers()[2]});
                            int i = new MultiThreesChecker().howManyAppeared(multiKeys, lotteryNumbers);
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
        ArrayList<MultiCombinationNumber> bestThrees = FileService.loadObject("IrishLottery/BestThrees");
        bestThrees.addAll(multiCombinationKeys);
        FileService.saveObject(bestThrees, "IrishLottery/BestThrees");
    }
}
