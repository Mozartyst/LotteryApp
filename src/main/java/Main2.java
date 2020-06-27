import dataSupport.FileService;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        for (int i = 5; i >=0; i--) {

            System.out.println(new Proposition(i).forMultiCombination());
            if (i != 0) {
                ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("FullLotteryNumbersFile");
                System.out.println(lotteryNumbers.get(i - 1));
            }
        }
    }
}
