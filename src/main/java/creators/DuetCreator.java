package creators;

import entity.*;

import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class DuetCreator {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final Properties properties;
    private final ArrayList<Duet> duets = new ArrayList<>();

    public DuetCreator(ArrayList<OneDraw> lotteryNumbers, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.properties = properties;
    }

    public void createDuets() {
        for (OneDraw lotteryNumbers : lotteryNumbers) {
            ArrayList<Integer> currentGameList = lotteryNumbers.getDrawNumbers();
            int index = this.lotteryNumbers.indexOf(lotteryNumbers);
            for (Integer number : currentGameList) {
                if (currentGameList.contains(number + 1)) {
                    /*DUET*/
                    if (!currentGameList.contains(number + 2)
                            && !currentGameList.contains(number - 1)) {
                        CombinationNumbers keyPairs = new CombinationNumbers(number, number + 1);
                        if (index < this.lotteryNumbers.size() - 1) {
                            Duet duet = new Duet(keyPairs);
                            duet.addOccurrence(1);
                            for (Integer number1 : this.lotteryNumbers.get(index + 1).getDrawNumbers()) {
                                int up1 = 0;
                                int up2 = 0;
                                int down1 = 0;
                                int down2 = 0;
                                if (keyPairs.getFirstNumber() == 1) {
                                    up1 = Integer.parseInt(properties.getProperty("range"));
                                    up2 = Integer.parseInt(properties.getProperty("range"));
                                } else if (keyPairs.getFirstNumber() == 2) {
                                    up2 = Integer.parseInt(properties.getProperty("range")) + 1;
                                } else if (keyPairs.getSecondNumber() == 46) {
                                    down2 = Integer.parseInt(properties.getProperty("range"));
                                } else if (keyPairs.getSecondNumber() == 47) {
                                    down1 = Integer.parseInt(properties.getProperty("range"));
                                    down2 = Integer.parseInt(properties.getProperty("range"));
                                }
                                if (keyPairs.getFirstNumber() == number1 + 1 - up1
                                        || keyPairs.getFirstNumber() == number1 + 2 - up2
                                        || keyPairs.getSecondNumber() == number1 - 1 + down1
                                        || keyPairs.getSecondNumber() == number1 - 2 + down2) {
                                    if (duets.contains(duet)) {
                                        duet = duets.get(duets.indexOf(duet));
                                        duet.addOccurrence(1);
                                    }
                                    duet.addNumberOfWinnings(1);

                                    if (keyPairs.getFirstNumber() == number1 + 1 - up1
                                            || keyPairs.getFirstNumber() == number1 + 2 - up2) {
                                        TreeMap<Integer, Integer> map;
                                        if (duet.getGoDown() == null) {
                                            map = new TreeMap<>();
                                            map.put(number1, 1);
                                        } else {
                                            map = duet.getGoDown();
                                            if (!map.containsKey(number1)) {
                                                map.put(number1, 1);
                                            } else {
                                                map.put(number1, map.get(number1) + 1);
                                            }
                                        }
                                        duet.setGoDown(map);
                                    } else {
                                        TreeMap<Integer, Integer> map;
                                        if (duet.getGoUp() == null) {
                                            map = new TreeMap<>();
                                            map.put(number1, 1);
                                        } else {
                                            map = duet.getGoUp();
                                            if (!map.containsKey(number1)) {
                                                map.put(number1, 1);
                                            } else {
                                                map.put(number1, map.get(number1) + 1);
                                            }
                                        }
                                        duet.setGoUp(map);
                                    }
                                }
                            }
                            if (!duets.contains(duet)) {
                                duets.add(duet);
                            }
                        }
                        /*TRIPLE*/
                    } else if (currentGameList.contains(number + 2)
                            && !currentGameList.contains(number + 3)
                            && !currentGameList.contains(number - 1)) {
                        CombinationNumbers tripleNumbers =
                                new CombinationNumbers(number, number + 1, number + 2);
                        if (index < this.lotteryNumbers.size() - 1) {
                            for (Integer number1 : this.lotteryNumbers.get(index + 1).getDrawNumbers()) {
                                Duet duet = new Duet(tripleNumbers);
                                duet.addOccurrence(1);
                                int up1 = 0;
                                int up2 = 0;
                                int down1 = 0;
                                int down2 = 0;
                                if (tripleNumbers.getFirstNumber() == 1) {
                                    up1 = Integer.parseInt(properties.getProperty("range"));
                                    up2 = Integer.parseInt(properties.getProperty("range"));
                                } else if (tripleNumbers.getFirstNumber() == 2) {
                                    up2 = Integer.parseInt(properties.getProperty("range")) + 1;
                                } else if (tripleNumbers.getThirdNumber() == 46) {
                                    down2 = Integer.parseInt(properties.getProperty("range"));
                                } else if (tripleNumbers.getThirdNumber() == 47) {
                                    down1 = Integer.parseInt(properties.getProperty("range"));
                                    down2 = Integer.parseInt(properties.getProperty("range"));
                                }
                                if (tripleNumbers.getFirstNumber() == number1 + 1 - up1
                                        || tripleNumbers.getFirstNumber() == number1 + 2 - up2
                                        || tripleNumbers.getThirdNumber() == number1 - 1 + down1
                                        || tripleNumbers.getThirdNumber() == number1 - 2 + down2) {
                                    if (duets.contains(duet)) {
                                        duet = duets.get(duets.indexOf(duet));
                                        duet.addOccurrence(1);
                                    }
                                    duet.addNumberOfWinnings(1);

                                    if (tripleNumbers.getFirstNumber() == number1 + 1 - up1
                                            || tripleNumbers.getFirstNumber() == number1 + 2 - up2) {
                                        TreeMap<Integer, Integer> map;
                                        if (duet.getGoDown() == null) {
                                            map = new TreeMap<>();
                                            map.put(number1, 1);
                                        } else {
                                            map = duet.getGoDown();
                                            if (!map.containsKey(number1)) {
                                                map.put(number1, 1);
                                            } else {
                                                map.put(number1, map.get(number1) + 1);
                                            }
                                        }
                                        duet.setGoDown(map);
                                    } else {
                                        TreeMap<Integer, Integer> map;
                                        if (duet.getGoUp() == null) {
                                            map = new TreeMap<>();
                                            map.put(number1, 1);
                                        } else {
                                            map = duet.getGoUp();
                                            if (!map.containsKey(number1)) {
                                                map.put(number1, 1);
                                            } else {
                                                map.put(number1, map.get(number1) + 1);
                                            }
                                        }
                                        duet.setGoUp(map);
                                    }
                                    if (!duets.contains(duet)) {
                                        duets.add(duet);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
