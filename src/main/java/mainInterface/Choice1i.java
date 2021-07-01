package mainInterface;

import entity.OneDraw;
import printers.NumbersPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice1i {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        new NumbersPrinter().print(properties);
    }
}
