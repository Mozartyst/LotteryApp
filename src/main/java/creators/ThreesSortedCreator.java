package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class ThreesSortedCreator {

    public void createFileBySortedThrees(String fileName, TreeMap<CombinationNumbers, Integer> allThreesInLottery) throws IOException {
        TreeMap<Integer, ArrayList<CombinationNumbers>> sortedListByNumber = new TreeMap<>();
        allThreesInLottery.forEach((com, val) -> {
            for (int i = 0; i <=2 ; i++) {
                Integer number = com.getNumbers()[i];
                if (sortedListByNumber.containsKey(number)){
                    sortedListByNumber.get(number).add(com);
                }else {
                    ArrayList<CombinationNumbers> combinationList = new ArrayList<>();
                    combinationList.add(com);
                    sortedListByNumber.put(number,combinationList);
                }
            }
        });

        FileService.saveObject(sortedListByNumber, fileName);
    }
}
