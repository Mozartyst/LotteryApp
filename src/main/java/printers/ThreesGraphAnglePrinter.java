package printers;

import entity.CombinationNumbers;
import entity.OneDraw;
import creators.ThreesCreatorFromDrawsHistory;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ThreesGraphAnglePrinter {
    private Set<CombinationNumbers> combinationNumbers;
    private TreeMap<Integer, Set<CombinationNumbers>> numbers = new TreeMap<>();
    private TreeMap<Integer, Set<CombinationNumbers>> indexes = new TreeMap<>();

    public void get(ArrayList<OneDraw> lotteryNumbers) {
        int[][][] ints = new int[48][48][48];
        int[][] intsSquare = new int[48][48];
        combinationNumbers = new ThreesCreatorFromDrawsHistory(lotteryNumbers).get();
        for (CombinationNumbers com : this.combinationNumbers) {
            ints[com.getFirstNumber()][com.getSecondNumber()][com.getThirdNumber()] = com.getIndexesWhereAppeared().size();
//            addNumber(com.getFirstNumber(), com);
//            addNumber(com.getSecondNumber(), com);
//            addNumber(com.getThirdNumber(), com);
            for (Integer index : com.getIndexesWhereAppeared()) {
                addIndex(index, com);
            }
        }
        for (int i = 1; i < 46; i++) {
            System.out.println(i);
            System.out.println("\u001B[36m" + "   001 002 003 004 005 006 007 008 009 " +
                    "010 011 012 013 014 015 016 017 018 019 " +
                    "020 021 022 023 024 025 026 027 028 029 " +
                    "030 031 032 033 034 035 036 037 038 039 " +
                    "040 041 042 043 044 045 046 047");
            for (int j = 1; j < 47; j++) {
                System.out.print("\u001B[36m" + String.format("%03d", j) + "\u001B[0m");
                for (int k = 1; k < 48; k++) {
                    if (k > j) {

                        if (ints[i][j][k] == 1) {
                            System.out.print("\u001B[33m" + " " + String.format("%03d", ints[i][j][k]));
                        } else if (ints[i][j][k] == 2) {
                            System.out.print("\u001B[32m" + " " + String.format("%03d", ints[i][j][k]));
                        } else if (ints[i][j][k] == 3) {
                            System.out.print("\u001B[34m" + " " + String.format("%03d", ints[i][j][k]));
                        } else if (ints[i][j][k] == 4) {
                            System.out.print("\u001B[35m" + " " + String.format("%03d", ints[i][j][k]));
                        } else if (ints[i][j][k] == 5) {
                            System.out.print("\u001B[31m" + " " + String.format("%03d", ints[i][j][k]));
                        } else {
                            System.out.print("\u001B[30m" + " " + String.format("%03d", ints[i][j][k]));
                        }
                    }else {
                        if (ints[i][k][j] == 1) {
                            System.out.print("\u001B[33m" + " " + String.format("%03d", ints[i][k][j]));
                        } else if (ints[i][k][j] == 2) {
                            System.out.print("\u001B[32m" + " " + String.format("%03d", ints[i][k][j]));
                        } else if (ints[i][k][j] == 3) {
                            System.out.print("\u001B[34m" + " " + String.format("%03d", ints[i][k][j]));
                        } else if (ints[i][k][j] == 4) {
                            System.out.print("\u001B[35m" + " " + String.format("%03d", ints[i][k][j]));
                        } else if (ints[i][k][j] == 5) {
                            System.out.print("\u001B[31m" + " " + String.format("%03d", ints[i][k][j]));
                        } else {
                            System.out.print("\u001B[30m" + " " + String.format("%03d", ints[i][k][j]));
                        }
                    }
                }
                System.out.println();

            }
        }
    }

    private void addNumber(Integer number, CombinationNumbers combinationNumbers) {
        if (numbers.containsKey(number)) {
            numbers.get(number).add(combinationNumbers);
        } else {
            Set<CombinationNumbers> combinationNumbers1 = new TreeSet<>();
            combinationNumbers1.add(combinationNumbers);
            numbers.put(number, combinationNumbers1);
        }
    }

    private void addIndex(Integer number, CombinationNumbers combinationNumbers) {
        if (indexes.containsKey(number)) {
            indexes.get(number).add(combinationNumbers);
        } else {
            Set<CombinationNumbers> combinationNumbers1 = new TreeSet<>();
            combinationNumbers1.add(combinationNumbers);
            indexes.put(number, combinationNumbers1);
        }
    }
}
