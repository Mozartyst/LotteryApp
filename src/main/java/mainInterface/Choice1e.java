package mainInterface;

import entity.OneDraw;
import printers.MultiThreesPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice1e {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        new MultiThreesPrinter().print(properties);
    }
}
