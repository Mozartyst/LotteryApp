package mainInterface;

import entity.OneDraw;
import printers.AllDrawsPrinter;

import java.util.ArrayList;
import java.util.Scanner;

public class Choice1f {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner) {
        new AllDrawsPrinter().print(lotteryNumbers);
    }
}
