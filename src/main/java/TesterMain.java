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

    public static void main(String[] args) {
        System.out.println(new Algorithm().getPropositionList(1));

    }
}

