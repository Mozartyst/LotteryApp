package printers;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.Comparator;


public class PrintRepeated {
    private final ArrayList<ArrayList<Integer>> fullList = new ArrayList<>();
    private final ArrayList<OneDraw> lotteryNumbers;

    public PrintRepeated(ArrayList<OneDraw> lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }

    public void print(int manyNumbers) {
        for (OneDraw weeklyNumbers : lotteryNumbers) {
            for (OneDraw nextWeeklyNumbers : lotteryNumbers) {
                if (lotteryNumbers.indexOf(nextWeeklyNumbers) <= lotteryNumbers.indexOf(weeklyNumbers)) {
                    continue;
                }
                ArrayList<Integer> printList = new ArrayList<>();
                int first = nextWeeklyNumbers.getDrawNumbers().get(0);
                int second = nextWeeklyNumbers.getDrawNumbers().get(1);
                int third = nextWeeklyNumbers.getDrawNumbers().get(2);
                int fourth = nextWeeklyNumbers.getDrawNumbers().get(3);
                int fifth = nextWeeklyNumbers.getDrawNumbers().get(4);
                int sixth = nextWeeklyNumbers.getDrawNumbers().get(5);
                if (weeklyNumbers.getDrawNumbers().contains(first)) {
                    printList.add(first);
                    if (weeklyNumbers.getDrawNumbers().contains(second)) {
                        printList.add(second);
                        if (weeklyNumbers.getDrawNumbers().contains(third)) {
                            printList.add(third);
                            if (weeklyNumbers.getDrawNumbers().contains(fourth)) {
                                printList.add(fourth);
                                if (weeklyNumbers.getDrawNumbers().contains(fifth)) {
                                    printList.add(fifth);
                                    if (weeklyNumbers.getDrawNumbers().contains(sixth)) {
                                        printList.add(sixth);
                                    }
                                }
                            }
                        }
                    } else if (weeklyNumbers.getDrawNumbers().contains(third)) {
                        printList.add(third);
                        if (weeklyNumbers.getDrawNumbers().contains(fourth)) {
                            printList.add(fourth);
                            if (weeklyNumbers.getDrawNumbers().contains(fifth)) {
                                printList.add(fifth);
                                if (weeklyNumbers.getDrawNumbers().contains(sixth)) {
                                    printList.add(sixth);
                                }
                            }
                        }
                    }
                } else if (weeklyNumbers.getDrawNumbers().contains(second)) {
                    printList.add(second);
                    if (weeklyNumbers.getDrawNumbers().contains(third)) {
                        printList.add(third);
                        if (weeklyNumbers.getDrawNumbers().contains(fourth)) {
                            printList.add(fourth);
                            if (weeklyNumbers.getDrawNumbers().contains(fifth)) {
                                printList.add(fifth);
                                if (weeklyNumbers.getDrawNumbers().contains(sixth)) {
                                    printList.add(sixth);
                                }
                            }
                        }
                    }
                } else if (weeklyNumbers.getDrawNumbers().contains(third)) {
                    printList.add(third);
                    if (weeklyNumbers.getDrawNumbers().contains(fourth)) {
                        printList.add(fourth);
                        if (weeklyNumbers.getDrawNumbers().contains(fifth)) {
                            printList.add(fifth);
                            if (weeklyNumbers.getDrawNumbers().contains(sixth)) {
                                printList.add(sixth);
                            }
                        }
                    }
                } else if (weeklyNumbers.getDrawNumbers().contains(fourth)) {
                    printList.add(fourth);
                    if (weeklyNumbers.getDrawNumbers().contains(fifth)) {
                        printList.add(fifth);
                        if (weeklyNumbers.getDrawNumbers().contains(sixth)) {
                            printList.add(sixth);
                        }
                    }
                }
                if (!printList.isEmpty()) {
                    fullList.add(printList);
                }
            }
        }
        fullList.sort(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (o1.size() == 1) {
                    if (o1.get(0).compareTo(o2.get(0)) == 0)
                        if (o2.size() > 1)
                            return -1;
                        else return 0;
                    else return o1.get(0).compareTo(o2.get(0));
                } else if (o1.size() == 2) {
                    if (o1.get(0).compareTo(o2.get(0)) == 0)
                        if (o2.size() > 1)
                            if (o1.get(1).compareTo(o2.get(1)) == 0)
                                if (o2.size() > 2)
                                    return -1;
                                else return 0;
                            else return o1.get(1).compareTo(o2.get(1));
                        else return 1;
                    else return o1.get(0).compareTo(o2.get(0));
                } else if (o1.size() == 3) {
                    if (o1.get(0).compareTo(o2.get(0)) == 0)
                        if (o2.size() > 1)
                            if (o1.get(1).compareTo(o2.get(1)) == 0)
                                if (o2.size() > 2)
                                    if (o1.get(2).compareTo(o2.get(2)) == 0)
                                        if (o2.size() > 3)
                                            return -1;
                                        else return 0;
                                    else return o1.get(2).compareTo(o2.get(2));
                                else return 1;
                            else return o1.get(1).compareTo(o2.get(1));
                        else return 1;
                    else return o1.get(0).compareTo(o2.get(0));
                } else if (o1.size() == 4) {
                    if (o1.get(0).compareTo(o2.get(0)) == 0)
                        if (o2.size() > 1)
                            if (o1.get(1).compareTo(o2.get(1)) == 0)
                                if (o2.size() > 2)
                                    if (o1.get(2).compareTo(o2.get(2)) == 0)
                                        if (o2.size() > 3)
                                            if (o1.get(3).compareTo(o2.get(3)) == 0)
                                                return 0;
                                            else return o1.get(3).compareTo(o2.get(3));
                                        else return 1;
                                    else return o1.get(2).compareTo(o2.get(2));
                                else return 1;
                            else return o1.get(1).compareTo(o2.get(1));
                        else return 1;
                    else return o1.get(0).compareTo(o2.get(0));
                } else {
                    if (o1.get(0).compareTo(o2.get(0)) == 0)
                        if (o2.size() > 1)
                            if (o1.get(1).compareTo(o2.get(1)) == 0)
                                if (o2.size() > 2)
                                    if (o1.get(2).compareTo(o2.get(2)) == 0)
                                        if (o2.size() > 3)
                                            if (o1.get(3).compareTo(o2.get(3)) == 0)
                                                if (o2.size() > 4)
                                                    if (o1.get(4).compareTo(o2.get(4)) == 0)
                                                        return 0;
                                                    else return o1.get(4).compareTo(o2.get(4));
                                                else return 1;
                                            else return o1.get(3).compareTo(o2.get(3));
                                        else return 1;
                                    else return o1.get(2).compareTo(o2.get(2));
                                else return 1;
                            else return o1.get(1).compareTo(o2.get(1));
                        else return 1;
                    else return o1.get(0).compareTo(o2.get(0));
                }
            }
        });
        final int[] counter = {1};
        fullList.forEach((x) -> {

            if (x.size() == manyNumbers) {
                System.out.println(counter[0] + " " + x);
                counter[0]++;
            }
        });
    }
}
