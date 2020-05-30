import algorithm.Algorithm;
import dataSupport.FileService;
import dataSupport.PolishLotteryDownloader;
import entity.MultiCombinationKeys;
import entity.ObjectForFileService;
import lottoPropositions.NumbersAfterMultiCombinations;
import lottoPropositions.Proposition;
import support.EachWithEveryOne;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class TesterMain {
    private static ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LotteryNumbersFile");

    public static void main(String[] args) {
        System.out.println("2 " + new Algorithm().getPropositionList(2));
        System.out.println("1 " + new Algorithm().getPropositionList(1));
        System.out.println("0 " + new Algorithm().getPropositionList(0));
        for (int i = 1; i < 12; i++) {

            System.out.println(i+2 + " " + new Algorithm().getPropositionList(i+2));
            System.out.println(i+1 + " " + new Algorithm().getPropositionList(i+1));
            System.out.println(i + " " + new Algorithm().getPropositionList(i));
            System.out.println(i + " " +lotteryNumbers.get(i - 1));
        }
    }
}

