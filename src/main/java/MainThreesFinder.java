import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.OneDraw;
import threeHunter.BestThreesFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class MainThreesFinder {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TreeMap<CombinationNumbers, Integer> allThreesInLottery = FileService.loadObject("IrishLottery/ThreesInIrishLottery");
        ArrayList<CombinationNumbers> listOfCombinations = new ArrayList<>();
        allThreesInLottery.forEach((com, val) -> {
            if (val == 2) {
                listOfCombinations.add(com);
            }
        });
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject("IrishLottery/FullIrishNumbersFile");
        Thread thread1 = new Thread(new BestThreesFinder(allThreesInLottery, listOfCombinations, lotteryNumbers, 0));
        Thread thread2 = new Thread(new BestThreesFinder(allThreesInLottery, listOfCombinations, lotteryNumbers, 1));
        Thread thread3 = new Thread(new BestThreesFinder(allThreesInLottery, listOfCombinations, lotteryNumbers, 2));
        Thread thread4 = new Thread(new BestThreesFinder(allThreesInLottery, listOfCombinations, lotteryNumbers, 3));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


//        ArrayList<MultiCombinationKeys> multi = FileService.loadObject("BestThrees");
//        ArrayList<ArrayList<Integer>> lotto = FileService.loadObject("FullIrishNumbersFile");
//        multi.forEach((m)->{
//            int i = new ThreesChecker().howManyAppeared(m, lotto);
//            if (i>=40) {
//                System.out.println(m + " = " + i);
//            }
//        });


//        int oneOne = 1;
//        int twoOne = 9;
//        int threeO = 39;
//        int oneTwo = 2;
//        int twoTwo = 27;
//        int threeT = 42;
//        int oneThr = 38;
//        int twoThr = 42;
//        int three3 = 45;
//        System.out.println(new ThreesChecker().howManyAppeared(new MultiCombinationKeys(new CombinationNumbers(oneOne, twoOne, threeO), new CombinationNumbers(oneTwo, twoTwo, threeT), new CombinationNumbers(oneThr, twoThr, three3)), lotto));

    }
}
