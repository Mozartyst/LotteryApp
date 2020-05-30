import dataSupport.FileService;

import java.util.*;

public class Wojtek {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LotteryNumbersFile");


        System.out.println(getIndexesIfPass(lotteryNumbers));
    }

    private static ArrayList<TreeSet<Integer>> getRandomListOfThrees() {
        List<Integer> newList = new LinkedList<>();
        ArrayList<TreeSet<Integer>> lista = new ArrayList<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        Random random = new Random();

        for (int i = 1; i <=47 ; i++) {
            newList.add(i);
        }

        Collections.shuffle(newList);

        for (int i = 0; i < newList.size() ; i++) {
            Integer bid = random.nextInt(47);

            if (treeSet.size() != 3) {
                treeSet.add(newList.get(bid));
            } else {
                lista.add(treeSet);
                treeSet = new TreeSet<>();
            }
        }
        System.out.println(lista);
        return lista;
    }

    public static TreeMap<Integer, ArrayList<TreeSet<Integer>>> getIndexesIfPass(ArrayList<ArrayList<Integer>> lotteryNumbers) {

        TreeMap<Integer, ArrayList<TreeSet<Integer>>> arrayListOfIndexes = new TreeMap<>();
        ArrayList<TreeSet<Integer>> randomThrees = getRandomListOfThrees();
        ArrayList<TreeSet<Integer>> tempArray = new ArrayList<>();

        for (int i = 0; i < lotteryNumbers.size() ; i++) {
            for (int j = 0; j < randomThrees.size() ; j++) {
                if (lotteryNumbers.get(i).containsAll(randomThrees.get(j))) {
                    tempArray.add(randomThrees.get(j));
                }
            }
            if (tempArray.size() > 0) {
                arrayListOfIndexes.put(lotteryNumbers.indexOf(lotteryNumbers.get(i)), tempArray);
            }
            tempArray = new ArrayList<>();
        }
        return arrayListOfIndexes;
    }
}
