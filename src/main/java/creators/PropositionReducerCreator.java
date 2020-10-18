package creators;

import dataSupport.FileService;
import entity.OneDraw;
import lombok.SneakyThrows;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

public class PropositionReducerCreator implements Runnable {
    private final TreeMap<Integer, ArrayList<Double>> results;
    private final int start;
    private final int end;
    private final Properties properties;
    private final TreeMap<Integer, TreeMap<Integer, Integer>> appearedNumbers; // IrishLottery/QuantityOfAppearedNumbers
    private final ArrayList<OneDraw> lotteryNumbers; // lastYearNumbers

    public PropositionReducerCreator(int start, int end, TreeMap<Integer, ArrayList<Double>> results, Properties properties, TreeMap<Integer, TreeMap<Integer, Integer>> appearedNumbers, ArrayList<OneDraw> lotteryNumbers) throws IOException, ClassNotFoundException {
        this.start = start;
        this.end = end;
        this.results = results;
        this.properties = properties;
        this.appearedNumbers = appearedNumbers;
        this.lotteryNumbers = lotteryNumbers;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = start; i >= end; i--) {
            TreeSet<Integer> tempPropositionList = null;
            try {
                tempPropositionList = new Proposition(i).forMultiCombination(properties);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<Integer> weeklyNumbers = lotteryNumbers.get(i - 1).getDrawNumbers();
            for (Integer number : weeklyNumbers) {
                if (tempPropositionList.contains(number)) {
                    Integer appearedValue = appearedNumbers.get(i).get(number);
                    addResult(number, (appearedValue / (double) ((i - 1) * 100)));
                } else if (tempPropositionList.contains(number + 1)) {

                } else if (tempPropositionList.contains(number - 1)) {

                } else {

                }
            }
        }
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void addResult(Integer number, Double value) {
        if (results.containsKey(number)) {
            results.get(number).add(value);
        } else {
            ArrayList<Double> doubles = new ArrayList<>();
            doubles.add(value);
            results.put(number, doubles);
        }
    }

    private synchronized void save() throws IOException {
        FileService.saveObject(results, properties.getProperty("results"));
    }
}
