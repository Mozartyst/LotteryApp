package mainInterface;

import dataSupport.FileService;
import lottoPropositions.NumbersAfterTriple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Choice10 {
    public void run(Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.println("Input index:");
        int index = scanner.nextInt();
        TreeMap<Integer, Integer> lotteryNumbersFile = new NumbersAfterTriple(FileService.loadObject("IrishLottery/FullIrishNumbersFile")).getPropositionNumbersAfterTriple(index);
        System.out.println(lotteryNumbersFile);
        if (index > 0) {
            ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("IrishLottery/FullIrishNumbersFile");
            System.out.println(lotteryNumbers.get(index - 1));
        }
    }
}
