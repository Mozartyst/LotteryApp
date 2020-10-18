package lottoPropositions;

import dataSupport.FileService;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class Proposition {
    Integer index;
    TreeSet<Integer> propositionList = new TreeSet<>();

    public Proposition(Integer index) {
        this.index = index;
    }

    public TreeSet<Integer> forMultiCombination(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        TreeMap<Integer, TreeMap<Integer, Boolean>> algorithm = FileService.loadObject(properties.getProperty("algorithmFile"));
        TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbers).getProposition(index, properties);

        multiProposition.forEach((number, value) -> {
            if (algorithm.containsKey(number)) {
                algorithm.get(number).forEach((x, y) -> {
                    if (lotteryNumbers.get(index).getDrawNumbers().contains(x)) {
                        propositionList.add(number);
                    }
                });
            }
        });
        return propositionList;
    }

}

