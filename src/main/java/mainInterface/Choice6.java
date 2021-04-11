package mainInterface;

import creators.threes.AllThreesCreator;
import creators.threes.ThreesCreatorFromDrawsHistory;
import creators.multiThrees.MultiThreesCreatorInRange;
import entity.CombinationNumbers;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class Choice6 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException, InterruptedException {
        Set<CombinationNumbers> combinationNumbers;
        System.out.println("Choice CombinationNumber:");
        System.out.println("1 - Combination create from draws history,");
        System.out.println("2 - Combination create from all numbers,");
        int choice = scanner.nextInt();
        if (choice == 1) {
            combinationNumbers = new ThreesCreatorFromDrawsHistory().get(lotteryNumbers);
        } else {
            combinationNumbers = new AllThreesCreator().get(lotteryNumbers, properties);
        }
        System.out.println("Put number for bottom range for double threes: ");
        int rangeFromForDoubleThrees = scanner.nextInt();
        System.out.println("Put number for top range for double threes: ");
        int rangeToForDoubleThrees = scanner.nextInt();
        System.out.println("Put number for bottom range for triple threes: ");
        int rangeFromForTripleThrees = scanner.nextInt();
        System.out.println("Put number for top range for triple threes: ");
        int rangeToForTripleThrees = scanner.nextInt();
        new MultiThreesCreatorInRange().create(
                combinationNumbers
                , properties
                , rangeFromForDoubleThrees
                , rangeToForDoubleThrees
                , rangeFromForTripleThrees
                , rangeToForTripleThrees
                , lotteryNumbers);
    }
}
