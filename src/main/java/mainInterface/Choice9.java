package mainInterface;

import creators.threes.LastDrawsThreesCreator;
import entity.OneDraw;
import java.util.ArrayList;
import java.util.Scanner;

public class Choice9 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner){
        System.out.println("Input range:");
        int range = scanner.nextInt();
        System.out.println("Input index:");
        int index = (lotteryNumbers.size() - 1) - scanner.nextInt();
        new LastDrawsThreesCreator().get(lotteryNumbers, range, index).forEach(System.out::println);
        if (index > 0) {
            System.out.println(lotteryNumbers.get(lotteryNumbers.size() - index).getDrawNumbers());
        }
    }
}
