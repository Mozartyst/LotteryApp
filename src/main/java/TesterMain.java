import algorithm.Algorithm;
import creators.QuantityOfAppearedCreator;
import dataSupport.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class TesterMain {
    private static ArrayList<ArrayList<Integer>> lotteryNumbers;

    static {
        try {
            lotteryNumbers = FileService.loadObject("FullLotteryNumbersFile");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        for (int i = 6; i >= 0; i--) {
            ArrayList<Integer> proposition = new Algorithm().getPropositionList(i);
            System.out.println(proposition);
//            System.out.println(lotteryNumbers.get(i - 1));
        }

    }
}

