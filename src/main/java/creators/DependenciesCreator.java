package creators;

import entity.*;
import entity.ObjectForFileService;
import dataSupport.FileService;
import entity.Number;
import support.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class DependenciesCreator {
    private final TreeMap<Integer, ArrayList<TreeMap<Integer, Boolean>>> dependenciesNAN = new TreeMap<>();
    private final TreeMap<Integer, ArrayList<TreeMap<Integer, Boolean>>> dependenciesNAP = new TreeMap<>();
    private final TreeMap<Integer, ArrayList<TreeMap<Integer, Boolean>>> dependenciesNAT = new TreeMap<>();
    private final ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadFile("LotteryNumbersFile");
    private final TreeMap<Integer, Number> listOfNumbers = (TreeMap<Integer, Number>) FileService.loadObject("ListOfNumbers").getObject();

    public void createDependencies() throws IOException {
        for (int i = lotteryNumbers.size() - 1; i > 0; i--) {
            ArrayList<Integer> numbersForCreateDependency = lotteryNumbers.get(i);
            ArrayList<Integer> numbersForBeingChecking = lotteryNumbers.get(i - 1);


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
        SUMUJE DEPENDENCJE
         */
        for (int i = 1; i < 48; i++) {
            ArrayList<Integer> isOverAndUnder = new ArrayList<>();
            ArrayList<TreeMap<Integer, Boolean>> isOverAverageAndHowMuch = this.dependenciesNAN.get(i);
            for (TreeMap<Integer, Boolean> integerBooleanTreeMap : isOverAverageAndHowMuch) {
                integerBooleanTreeMap.forEach((x, y) -> {
                    isOverAndUnder.add(x);
                });
            }

            TreeMap<Integer, Integer> favoriteOverAndUnder = new TreeMap<>();
            isOverAndUnder.forEach((x) -> {
                if (favoriteOverAndUnder.containsKey(x)) {
                    favoriteOverAndUnder.replace(x, favoriteOverAndUnder.get(x) + 1);
                } else favoriteOverAndUnder.put(x, 1);
            });

            Number number = listOfNumbers.get(i);
            Dependency dependency = number.getDependency();
            dependency.setIsOverAndUnderAverageNAN(favoriteOverAndUnder);
            number.setDependency(dependency);
        }

        for (int i = 1; i < 48; i++) {
            ArrayList<Integer> isOverAndUnder = new ArrayList<>();
            ArrayList<TreeMap<Integer, Boolean>> isOverAverageAndHowMuch = this.dependenciesNAP.get(i);
            for (TreeMap<Integer, Boolean> integerBooleanTreeMap : isOverAverageAndHowMuch) {
                integerBooleanTreeMap.forEach((x, y) -> {
                    isOverAndUnder.add(x);
                });
            }

            TreeMap<Integer, Integer> favoriteOverAndUnder = new TreeMap<>();
            isOverAndUnder.forEach((x) -> {
                if (favoriteOverAndUnder.containsKey(x)) {
                    favoriteOverAndUnder.replace(x, favoriteOverAndUnder.get(x) + 1);
                } else favoriteOverAndUnder.put(x, 1);
            });

            Number number = listOfNumbers.get(i);
            Dependency dependency = number.getDependency();
            dependency.setIsOverAndUnderAverageNAP(favoriteOverAndUnder);
            number.setDependency(dependency);
        }
        for (int i = 1; i < 48; i++) {
            ArrayList<Integer> isOverAndUnder = new ArrayList<>();
            ArrayList<TreeMap<Integer, Boolean>> isOverAverageAndHowMuch = this.dependenciesNAT.get(i);
            for (TreeMap<Integer, Boolean> integerBooleanTreeMap : isOverAverageAndHowMuch) {
                integerBooleanTreeMap.forEach((x, y) -> {
                    isOverAndUnder.add(x);
                });
            }

            TreeMap<Integer, Integer> favoriteOverAndUnder = new TreeMap<>();
            isOverAndUnder.forEach((x) -> {
                if (favoriteOverAndUnder.containsKey(x)) {
                    favoriteOverAndUnder.replace(x, favoriteOverAndUnder.get(x) + 1);
                } else favoriteOverAndUnder.put(x, 1);
            });

            Number number = listOfNumbers.get(i);
            Dependency dependency = number.getDependency();
            dependency.setIsOverAndUnderAverageNAT(favoriteOverAndUnder);
            number.setDependency(dependency);
        }
        ObjectForFileService<TreeMap<Integer, Number>> objectForFileService = new ObjectForFileService<>();
        objectForFileService.setObject(listOfNumbers);
        FileService.saveObject(objectForFileService, "ListOfNumbers");
    }
}
