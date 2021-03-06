package printers;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.Properties;

public class AllDrawsGraph {
    public void print(ArrayList<OneDraw> lotteryNumbers, Properties properties) {
        for (OneDraw weekDraw : lotteryNumbers) {
            if (Boolean.parseBoolean(properties.getProperty("bonusBalls"))) {
                if (Boolean.parseBoolean(properties.getProperty("bonusTogether"))) {
                    for (int i = 1; i <= Integer.parseInt(properties.getProperty("range")); i++) {
                        if (weekDraw.getDrawNumbers().contains(i) || weekDraw.getBonusBalls().contains(i)) {
                            if (weekDraw.getBonusBalls().contains(i)) {
                                System.out.print("\u001B[41m" + "\u001B[30m" + String.format("%02d", i) + "\u001B[0m");
                                System.out.print("\u001B[33m" + "|" + "\u001B[0m");
                            } else {
                                System.out.print("\u001B[42m" + "\u001B[30m" + String.format("%02d", i) + "\u001B[0m");
                                System.out.print("\u001B[33m" + "|" + "\u001B[0m");
                            }
                        } else {
                            System.out.print("\u001B[33m" + "  |" + "\u001B[0m");
                        }
                    }
                } else {
                    for (int i = 1; i <= Integer.parseInt(properties.getProperty("range")); i++) {
                        if (weekDraw.getDrawNumbers().contains(i)) {
                            System.out.print("\u001B[42m" + "\u001B[30m" + String.format("%02d", i) + "\u001B[0m");
                            System.out.print("\u001B[33m" + "|" + "\u001B[0m");
                        } else {
                            System.out.print("\u001B[33m" + "  |" + "\u001B[0m");
                        }
                    }
                    for (int i = 1; i <= Integer.parseInt(properties.getProperty("bonusRange")); i++) {
                        if (weekDraw.getBonusBalls().contains(i)) {
                            System.out.print("\u001B[41m" + "\u001B[30m" + String.format("%02d", i) + "\u001B[0m");
                            System.out.print("\u001B[31m" + "|" + "\u001B[0m");
                        } else {
                            System.out.print("\u001B[31m" + "  |" + "\u001B[0m");
                        }
                    }
                }

            } else {
                for (int i = 1; i <= Integer.parseInt(properties.getProperty("range")); i++) {
                    if (weekDraw.getDrawNumbers().contains(i)) {
                        System.out.print("\u001B[42m" + "\u001B[30m" + String.format("%02d", i) + "\u001B[0m");
                        System.out.print("\u001B[33m" + "|" + "\u001B[0m");
                    } else {
                        System.out.print("\u001B[33m" + "  |" + "\u001B[0m");
                    }
                }
            }
            System.out.println();
        }
    }
}
