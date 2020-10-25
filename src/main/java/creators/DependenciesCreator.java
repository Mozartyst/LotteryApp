package creators;

import entity.*;
import dataSupport.FileService;
import entity.Number;
import support.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class DependenciesCreator {
    private final TreeMap<Integer, ArrayList<TreeMap<Integer, Boolean>>> dependenciesNAN = new TreeMap<>();
    private final TreeMap<Integer, ArrayList<TreeMap<Integer, Boolean>>> dependenciesNAP = new TreeMap<>();
    private final TreeMap<Integer, ArrayList<TreeMap<Integer, Boolean>>> dependenciesNAT = new TreeMap<>();
    private final ArrayList<OneDraw> lotteryNumbers;
    private final Properties properties;
    private final TreeMap<Integer, Number> listOfNumbers;

    public DependenciesCreator(ArrayList<OneDraw> lotteryNumbers, TreeMap<Integer, Number> listOfNumbers, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.listOfNumbers = listOfNumbers;
        this.properties = properties;
    }

    public void createDependencies() throws IOException {
        for (int i = 0; i < lotteryNumbers.size()-1; i++) {
            ArrayList<Integer> numbersForCreateDependency = lotteryNumbers.get(i).getDrawNumbers();
            ArrayList<Integer> numbersForBeingChecking = lotteryNumbers.get(i + 1).getDrawNumbers();


            // Single Number
            for (Integer numberForBeingChecking : numbersForBeingChecking) {
                ArrayList<TreeMap<Integer, Boolean>> listIsOverAverageAndHowMuch = new ArrayList<>();
                ArrayList<TreeMap<Integer, Boolean>> listIsOverAveragePairsAndHowMuch = new ArrayList<>();
                ArrayList<TreeMap<Integer, Boolean>> listIsOverAverageTripleAndHowMuch = new ArrayList<>();
                for (Integer numberForCreate : numbersForCreateDependency) {
                    TreeMap<Integer, Boolean> isOverAverageAndHowMuch = new TreeMap<>();
                    TreeMap<Integer, Integer> afterNumbers = listOfNumbers.get(numberForBeingChecking).getDependency().getAfterNumbers();

                    if (afterNumbers.containsKey(numberForCreate)) {

                        Integer howOften = afterNumbers.get(numberForCreate);
                        ArrayList<Integer> manyTimes = new ArrayList<>();
                        afterNumbers.forEach((x, y) -> manyTimes.add(y));
                        Integer average = Auxiliary.returnAverage(manyTimes);
                        listOfNumbers.get(numberForBeingChecking).getDependency().setAverageForNAN(average);
                        boolean isOverAverage;
                        isOverAverage = howOften > average;
                        isOverAverageAndHowMuch.put(howOften - average, isOverAverage);
                        listIsOverAverageAndHowMuch.add(isOverAverageAndHowMuch);

                    }


                    // Pairs
                    for (Integer numberForCreateSec : numbersForCreateDependency) {
                        if (numberForCreate > numberForCreateSec) {
                            continue;
                        }
                        CombinationNumbers keyPairs = new CombinationNumbers(numberForCreate, numberForCreateSec);
                        TreeMap<Integer, Boolean> isOverAveragePairsAndHowMuch = new TreeMap<>();
                        TreeMap<CombinationNumbers, Occurrences> afterPairs = listOfNumbers.get(numberForBeingChecking).getDependency().getAfterPairs();

                        if (afterPairs.containsKey(keyPairs)) {

                            Integer howOften = afterPairs.get(keyPairs).getHit();
                            ArrayList<Integer> manyTimes = new ArrayList<>();
                            afterPairs.forEach((x, y) -> manyTimes.add(y.getHit()));
                            Integer average = Auxiliary.returnAverage(manyTimes);
                            boolean isOverAverage = howOften > average;
                            isOverAveragePairsAndHowMuch.put(howOften - average, isOverAverage);
                            listIsOverAveragePairsAndHowMuch.add(isOverAveragePairsAndHowMuch);

                        }


                        // Triple
                        for (Integer numberForCreateThird : numbersForCreateDependency) {
                            if (numberForCreateSec > numberForCreateThird) {
                                continue;
                            }
                            CombinationNumbers combinationNumbers = new CombinationNumbers(numberForCreate, numberForCreateSec, numberForCreateThird);
                            TreeMap<Integer, Boolean> isOverAverageTripleAndHowMuch = new TreeMap<>();
                            TreeMap<CombinationNumbers, Occurrences> afterTriple = listOfNumbers.get(numberForBeingChecking).getDependency().getAfterTriple();

                            if (afterTriple.containsKey(combinationNumbers)) {

                                Integer howOften = afterTriple.get(combinationNumbers).getHit();
                                ArrayList<Integer> manyTimes = new ArrayList<>();
                                afterTriple.forEach((x, y) -> manyTimes.add(y.getHit()));
                                Integer average = Auxiliary.returnAverage(manyTimes);
                                boolean isOverAverage = howOften > average;
                                isOverAverageTripleAndHowMuch.put(howOften - average, isOverAverage);
                                listIsOverAverageTripleAndHowMuch.add(isOverAverageTripleAndHowMuch);

                            }

                        }
                        //Triple
                        if (dependenciesNAT.containsKey(numberForBeingChecking)) {
                            ArrayList<TreeMap<Integer, Boolean>> dependencies = this.dependenciesNAT.get(numberForBeingChecking);
                            dependencies.addAll(listIsOverAverageTripleAndHowMuch);
                            this.dependenciesNAT.put(numberForBeingChecking, dependencies);
                        } else {
                            dependenciesNAT.put(numberForBeingChecking, listIsOverAverageTripleAndHowMuch);
                        }
                    }
                    //Pair
                    if (dependenciesNAP.containsKey(numberForBeingChecking)) {
                        ArrayList<TreeMap<Integer, Boolean>> dependencies = this.dependenciesNAP.get(numberForBeingChecking);
                        dependencies.addAll(listIsOverAveragePairsAndHowMuch);
                        this.dependenciesNAP.put(numberForBeingChecking, dependencies);
                    } else {
                        dependenciesNAP.put(numberForBeingChecking, listIsOverAveragePairsAndHowMuch);
                    }
                }
                //Single
                if (dependenciesNAN.containsKey(numberForBeingChecking)) {
                    ArrayList<TreeMap<Integer, Boolean>> dependencies = this.dependenciesNAN.get(numberForBeingChecking);
                    dependencies.addAll(listIsOverAverageAndHowMuch);
                    this.dependenciesNAN.put(numberForBeingChecking, dependencies);
                } else {
                    dependenciesNAN.put(numberForBeingChecking, listIsOverAverageAndHowMuch);
                }
            }
        }
        /*
        ADDING UP DEPENDENCIES
         */
        for (int i = 1; i < 48; i++) {
            ArrayList<Integer> isOverAndUnder = new ArrayList<>();
            ArrayList<TreeMap<Integer, Boolean>> isOverAverageAndHowMuch = this.dependenciesNAN.get(i);
            for (TreeMap<Integer, Boolean> integerBooleanTreeMap : isOverAverageAndHowMuch) {
                integerBooleanTreeMap.forEach((x, y) -> isOverAndUnder.add(x));
            }

            TreeMap<Integer, Integer> favoriteOverAndUnder = new TreeMap<>();
            isOverAndUnder.forEach((x) -> {
                if (favoriteOverAndUnder.containsKey(x)) {
                    favoriteOverAndUnder.replace(x, favoriteOverAndUnder.get(x) + 1);
                } else favoriteOverAndUnder.put(x, 1);
            });

            Number number = listOfNumbers.get(i);
            Dependencies dependencies = number.getDependency();
            dependencies.setIsOverAndUnderAverageNAN(favoriteOverAndUnder);
            number.setDependency(dependencies);
        }

        for (int i = 1; i < 48; i++) {
            ArrayList<Integer> isOverAndUnder = new ArrayList<>();
            ArrayList<TreeMap<Integer, Boolean>> isOverAverageAndHowMuch = this.dependenciesNAP.get(i);
            for (TreeMap<Integer, Boolean> integerBooleanTreeMap : isOverAverageAndHowMuch) {
                integerBooleanTreeMap.forEach((x, y) -> isOverAndUnder.add(x));
            }

            TreeMap<Integer, Integer> favoriteOverAndUnder = new TreeMap<>();
            isOverAndUnder.forEach((x) -> {
                if (favoriteOverAndUnder.containsKey(x)) {
                    favoriteOverAndUnder.replace(x, favoriteOverAndUnder.get(x) + 1);
                } else favoriteOverAndUnder.put(x, 1);
            });

            Number number = listOfNumbers.get(i);
            Dependencies dependencies = number.getDependency();
            dependencies.setIsOverAndUnderAverageNAP(favoriteOverAndUnder);
            number.setDependency(dependencies);
        }
        for (int i = 1; i < 48; i++) {
            ArrayList<Integer> isOverAndUnder = new ArrayList<>();
            ArrayList<TreeMap<Integer, Boolean>> isOverAverageAndHowMuch = this.dependenciesNAT.get(i);
            for (TreeMap<Integer, Boolean> integerBooleanTreeMap : isOverAverageAndHowMuch) {
                integerBooleanTreeMap.forEach((x, y) -> isOverAndUnder.add(x));
            }

            TreeMap<Integer, Integer> favoriteOverAndUnder = new TreeMap<>();
            isOverAndUnder.forEach((x) -> {
                if (favoriteOverAndUnder.containsKey(x)) {
                    favoriteOverAndUnder.replace(x, favoriteOverAndUnder.get(x) + 1);
                } else favoriteOverAndUnder.put(x, 1);
            });

            Number number = listOfNumbers.get(i);
            Dependencies dependencies = number.getDependency();
            dependencies.setIsOverAndUnderAverageNAT(favoriteOverAndUnder);
            number.setDependency(dependencies);
        }
        FileService.saveObject(listOfNumbers, properties.getProperty("listOfNumbers"));
    }
}
