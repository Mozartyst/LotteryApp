package mainInterface;

import creators.threes.LastDrawsThreesCreator;
import entity.CombinationNumbers;
import entity.OneDraw;
import lottoPropositions.NumbersFromFewLastDraws;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Choice1d {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner) {
        System.out.println("Input range:");
        int range = scanner.nextInt();
        System.out.println("Input index:");
        int index = (lotteryNumbers.size() - 1) - scanner.nextInt();
        Set<CombinationNumbers> combinationNumbers = new LastDrawsThreesCreator().get(lotteryNumbers, range, index);
//        combinationNumbers.forEach(System.out::println);
        System.out.println(combinationNumbers.size());
        System.out.println(new NumbersFromFewLastDraws().get(lotteryNumbers, range, index));
        if (index < lotteryNumbers.size() - 1) {
            System.out.println(lotteryNumbers.get(index + 1).getDrawNumbers());
        }
    }
}
