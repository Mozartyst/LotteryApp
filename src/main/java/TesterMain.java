import dataSupport.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class TesterMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LastYearLotteryNumbersFile");
//        TreeMap<Integer, ArrayList<Double>> results = new TreeMap<>();
//        Thread thread = new Thread(new PropositionReducerCreator(lotteryNumbers.size(),35,results));
//        Thread thread1 = new Thread(new PropositionReducerCreator(34,25,results));
//        Thread thread2 = new Thread(new PropositionReducerCreator(24,15,results));
//        Thread thread3 = new Thread(new PropositionReducerCreator(14,1,results));
//        thread.start();
//        thread1.start();
//        thread2.start();
//        thread3.start();

        TreeMap<Integer, ArrayList<Double>> results = FileService.loadObject("IrishLottery/Results");
        results.forEach((number,result)->{
        System.out.println(number + " = " + result);

        });
    }
}

