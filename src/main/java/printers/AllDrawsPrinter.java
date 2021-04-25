package printers;

import entity.OneDraw;

import java.util.ArrayList;

public class AllDrawsPrinter {
    public void print(ArrayList<OneDraw> lotteryNumbers){
        lotteryNumbers.forEach(System.out::println);
    }
}
