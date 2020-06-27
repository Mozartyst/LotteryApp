import dataSupport.FileService;
import support.Auxiliary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class IsRepeatedSixNumbers {
    private static int counter = 1;
    private static ArrayList<ArrayList<Integer>> fullList = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> lotteryNumbers = Auxiliary.returnReversedListLotteryNumbers(FileService.loadObject("FullLotteryNumbersFile"));
        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            for (ArrayList<Integer> nextWeeklyNumbers : lotteryNumbers) {
                if (lotteryNumbers.indexOf(nextWeeklyNumbers) < lotteryNumbers.indexOf(weeklyNumbers)) {
                    continue;
                }
                ArrayList<Integer> printList = new ArrayList<>();
                int first = nextWeeklyNumbers.get(0);
                int second = nextWeeklyNumbers.get(1);
                int third = nextWeeklyNumbers.get(2);
                int fourth = nextWeeklyNumbers.get(3);
                int fifth = nextWeeklyNumbers.get(4);
                int sixth = nextWeeklyNumbers.get(5);
                if (weeklyNumbers.contains(first)) {
                    printList.add(first);
                    if (weeklyNumbers.contains(second)) {
                        printList.add(second);
                        if (weeklyNumbers.contains(third)) {
                            printList.add(third);
                            if (weeklyNumbers.contains(fourth)) {
                                printList.add(fourth);
                                if (weeklyNumbers.contains(fifth)) {
                                    printList.add(fifth);
                                    if (weeklyNumbers.contains(sixth)) {
                                        printList.add(sixth);
                                    }
                                }
                            }
                        }
                    } else if (weeklyNumbers.contains(third)) {
                        printList.add(third);
                        if (weeklyNumbers.contains(fourth)) {
                            printList.add(fourth);
                            if (weeklyNumbers.contains(fifth)) {
                                printList.add(fifth);
                                if (weeklyNumbers.contains(sixth)) {
                                    printList.add(sixth);
                                }
                            }
                        }
                    }
                } else if (weeklyNumbers.contains(second)) {
                    printList.add(second);
                    if (weeklyNumbers.contains(third)) {
                        printList.add(third);
                        if (weeklyNumbers.contains(fourth)) {
                            printList.add(fourth);
                            if (weeklyNumbers.contains(fifth)) {
                                printList.add(fifth);
                                if (weeklyNumbers.contains(sixth)) {
                                    printList.add(sixth);
                                }
                            }
                        }
                    }
                } else if (weeklyNumbers.contains(third)) {
                    printList.add(third);
                    if (weeklyNumbers.contains(fourth)) {
                        printList.add(fourth);
                        if (weeklyNumbers.contains(fifth)) {
                            printList.add(fifth);
                            if (weeklyNumbers.contains(sixth)) {
                                printList.add(sixth);
                            }
                        }
                    }
                } else if (weeklyNumbers.contains(fourth)) {
                    printList.add(fourth);
                    if (weeklyNumbers.contains(fifth)) {
                        printList.add(fifth);
                        if (weeklyNumbers.contains(sixth)) {
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
        print();
    }

    public static void print() {
        fullList.forEach((x) -> {

            if (x.size() == 5) {
                System.out.println(counter + " " + x);
                counter++;
            }
        });
    }
}
