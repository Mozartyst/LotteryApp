package creators;

import dataSupport.FileService;
import entity.*;
import entity.Number;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class DuetCreator {
    private ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("FullLotteryNumbersFile");
    private TreeMap<CombinationNumbers, ArrayList<Integer>> collectionPairsAndIndexes = new TreeMap<>();
    private TreeMap<CombinationNumbers, ArrayList<Integer>> collectionTripleAndIndexes = new TreeMap<>();
    private TreeMap<Integer, Number> listOfNumbers = FileService.loadObject("ListOfNumbers");
    private TreeMap<Integer, TreeMap<CombinationNumbers, Duet>> afterDuetForNumbers = new TreeMap<>();

    public DuetCreator() throws IOException, ClassNotFoundException {
    }

    public void createDuets() throws IOException {
        for (int i = lotteryNumbers.size() - 1; i > 0; i--) {
            ArrayList<Integer> currentGameList = lotteryNumbers.get(i);

            /* WYBÓR PIERWSZEJ LICZBY*/
            for (Integer number : currentGameList) {
                /* WYBÓR DRUGIEJ LICZBY*/
                if (currentGameList.contains(number + 1)) {
                    /*WYKLUCZENIE TRZECIEJ LICZBY*/
                    if (!currentGameList.contains(number + 2)
                            && !currentGameList.contains(number - 1)) {
                        CombinationNumbers keyPairs = new CombinationNumbers(number, number + 1);
                        if (collectionPairsAndIndexes.containsKey(keyPairs)) {
                            ArrayList<Integer> indexes = collectionPairsAndIndexes.get(keyPairs);
                            indexes.add(i);
                            collectionPairsAndIndexes.replace(keyPairs, indexes);
                        } else {
                            ArrayList<Integer> indexes = new ArrayList<>();
                            indexes.add(i);
                            collectionPairsAndIndexes.put(keyPairs, indexes);
                        }

                        /*WYBÓR TRZECIEJ LICZBY I WYKLUCZENIE CZWARTEJ*/
                    } else if (currentGameList.contains(number + 2)
                            && !currentGameList.contains(number + 3)
                            && !currentGameList.contains(number - 1)) {
                        CombinationNumbers combinationNumbers = new CombinationNumbers(number, number + 1, number + 2);
                        if (collectionTripleAndIndexes.containsKey(combinationNumbers)) {
                            ArrayList<Integer> indexes = collectionTripleAndIndexes.get(combinationNumbers);
                            indexes.add(i);
                            collectionTripleAndIndexes.replace(combinationNumbers, indexes);
                        } else {
                            ArrayList<Integer> indexes = new ArrayList<>();
                            indexes.add(i);
                            collectionTripleAndIndexes.put(combinationNumbers, indexes);
                        }
                    }
                }
            }
        }
        createDuetsForNumbers();
        FileService.saveObject(listOfNumbers, "ListOfNumbers");
    }

    private void createDuetsForNumbers(){
        collectionPairsAndIndexes.forEach((x, y) -> {
            final Duet[] duet = {new Duet(x)};
            y.forEach((index) -> {
                if (index > 0) {
                    lotteryNumbers.get(index - 1).forEach((number) -> {
                        if (afterDuetForNumbers.containsKey(number)) {
                            if (afterDuetForNumbers.get(number) != null) {
                                if (afterDuetForNumbers.get(number).containsKey(x)) {
                                    duet[0] = afterDuetForNumbers.get(number).get(x);
                                }
                            }
                        }
                        duet[0].setOccurred(y.size());
                        int up1 = 0;
                        int up2 = 0;
                        int down1 = 0;
                        int down2 = 0;
                        if (x.getFirstNumber() == 1) {
                            up1 = 47;
                            up2 = 47;
                        } else if (x.getFirstNumber() == 2) {
                            up2 = 48;
                        } else if (x.getSecondNumber() == 46) {
                            down2 = 47;
                        } else if (x.getSecondNumber() == 47) {
                            down1 = 47;
                            down2 = 47;
                        }
                        if (x.getFirstNumber() == number + 1 - up1
                                || x.getFirstNumber() == number + 2 - up2
                                || x.getSecondNumber() == number - 1 + down1
                                || x.getSecondNumber() == number - 2 + down2) {
                            if (duet[0].getNumberOfWinnings() == null) {
                                Integer integer = 1;
                                duet[0].setNumberOfWinnings(integer);
                            } else {
                                duet[0].setNumberOfWinnings(duet[0].getNumberOfWinnings() + 1);
                            }
                            if (x.getFirstNumber() == number + 1 - up1 || x.getFirstNumber() == number + 2 - up2) {
                                if (duet[0].getGoDown() == null) {
                                    TreeMap<Integer, Integer> map = new TreeMap<>();
                                    map.put(number, 1);
                                    duet[0].setGoDown(map);
                                } else {
                                    TreeMap<Integer, Integer> map = duet[0].getGoDown();
                                    if (map.get(number) == null) {
                                        map.put(number, 1);
                                    } else {
                                        map.put(number, map.get(number) + 1);
                                    }
                                    duet[0].setGoDown(map);
                                }
                            } else {
                                if (duet[0].getGoUp() == null) {
                                    TreeMap<Integer, Integer> map = new TreeMap<>();
                                    map.put(number, 1);
                                    duet[0].setGoUp(map);
                                } else {
                                    TreeMap<Integer, Integer> map = duet[0].getGoUp();
                                    if (map.get(number) == null) {
                                        map.put(number, 1);
                                    } else {
                                        map.put(number, map.get(number) + 1);
                                    }
                                    duet[0].setGoUp(map);
                                }
                            }
                            if (afterDuetForNumbers.get(number) == null) {
                                TreeMap<CombinationNumbers, Duet> keyDuetTreeMap = new TreeMap<>();
                                keyDuetTreeMap.put(x, duet[0]);
                                afterDuetForNumbers.put(number, keyDuetTreeMap);
                            } else {
                                TreeMap<CombinationNumbers, Duet> keyPairsDuetTreeMap;
                                keyPairsDuetTreeMap = afterDuetForNumbers.get(number);
                                keyPairsDuetTreeMap.put(x, duet[0]);
                                afterDuetForNumbers.put(number, keyPairsDuetTreeMap);
                            }
                        }
                    });

                }
            });
        });
        collectionTripleAndIndexes.forEach((x, y) -> {
            final Duet[] duet = {new Duet(x)};
            y.forEach((index) -> {
                if (index > 0) {
                    lotteryNumbers.get(index - 1).forEach((number) -> {
                        if (afterDuetForNumbers.containsKey(number)) {
                            if (afterDuetForNumbers.get(number) != null) {
                                if (afterDuetForNumbers.get(number).containsKey(x)) {
                                    duet[0] = afterDuetForNumbers.get(number).get(x);
                                }
                            }
                        }
                        duet[0].setOccurred(y.size());
                        int up1 = 0;
                        int up2 = 0;
                        int down1 = 0;
                        int down2 = 0;
                        if (x.getFirstNumber() == 1) {
                            up1 = 47;
                            up2 = 47;
                        } else if (x.getFirstNumber() == 2) {
                            up2 = 48;
                        } else if (x.getThirdNumber() == 46) {
                            down2 = 47;
                        } else if (x.getThirdNumber() == 47) {
                            down1 = 47;
                            down2 = 47;
                        }
                        if (x.getFirstNumber() == number + 1 - up1
                                || x.getFirstNumber() == number + 2 - up2
                                || x.getThirdNumber() == number - 1 + down1
                                || x.getThirdNumber() == number - 2 + down2) {
                            if (duet[0].getNumberOfWinnings() == null) {
                                Integer integer = 1;
                                duet[0].setNumberOfWinnings(integer);
                            } else {
                                duet[0].setNumberOfWinnings(duet[0].getNumberOfWinnings() + 1);
                            }
                            if (x.getFirstNumber() == number + 1 - up1 || x.getFirstNumber() == number + 2 - up2) {
                                if (duet[0].getGoDown() == null) {
                                    TreeMap<Integer, Integer> map = new TreeMap<>();
                                    map.put(number, 1);
                                    duet[0].setGoDown(map);
                                } else {
                                    TreeMap<Integer, Integer> map = duet[0].getGoDown();
                                    if (map.get(number) == null) {
                                        map.put(number, 1);
                                    } else {
                                        map.put(number, map.get(number) + 1);
                                    }
                                    duet[0].setGoDown(map);
                                }
                            } else {
                                if (duet[0].getGoUp() == null) {
                                    TreeMap<Integer, Integer> map = new TreeMap<>();
                                    map.put(number, 1);
                                    duet[0].setGoUp(map);
                                } else {
                                    TreeMap<Integer, Integer> map = duet[0].getGoUp();
                                    if (map.get(number) == null) {
                                        map.put(number, 1);
                                    } else {
                                        map.put(number, map.get(number) + 1);
                                    }
                                    duet[0].setGoUp(map);
                                }
                            }
                            if (afterDuetForNumbers.get(number) == null) {
                                TreeMap<CombinationNumbers, Duet> keyDuetTreeMap = new TreeMap<>();
                                keyDuetTreeMap.put(x, duet[0]);
                                afterDuetForNumbers.put(number, keyDuetTreeMap);
                            } else {
                                TreeMap<CombinationNumbers, Duet> keyPairsDuetTreeMap;
                                keyPairsDuetTreeMap = afterDuetForNumbers.get(number);
                                keyPairsDuetTreeMap.put(x, duet[0]);
                                afterDuetForNumbers.put(number, keyPairsDuetTreeMap);
                            }
                        }
                    });

                }
            });
        });
        afterDuetForNumbers.forEach((x, y) -> {
            y.forEach((key, duet) -> {
                int value;
                if (duet.getGoUp() != null) {
                    if (duet.getGoUp().containsKey(x)) {
                        value = duet.getGoUp().get(x);
                    } else {
                        value = duet.getGoDown().get(x);
                    }
                } else value = duet.getGoDown().get(x);
                TreeMap<Duet, Integer> afterDuet = new TreeMap<>();
                afterDuet.put(duet, value);
                listOfNumbers.get(x).getDependency().addAfterDuet(afterDuet);
            });
        });
    }
}
