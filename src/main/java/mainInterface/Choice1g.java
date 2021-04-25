package mainInterface;

import entity.OneDraw;
import printers.AllDrawsGraph;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice1g {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) {
        new AllDrawsGraph().print(lotteryNumbers,properties);
    }

}
