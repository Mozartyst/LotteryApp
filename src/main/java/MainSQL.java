import dataSupport.FileService;
import entity.MultiCombinationKeys;
import entity.ObjectForFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class MainSQL {
    public static void main(String[] args) {
//        new MultiCombinationKeysDAO().dropTable("compar");
//        new MultiCombinationKeysDAO().createTable("multiCombination");
        TreeSet<MultiCombinationKeys> afterMultiCombinationKey = (TreeSet<MultiCombinationKeys>) FileService.loadObject("TreeSetReducedAfterMulti").getObject();
        ArrayList<MultiCombinationKeys> multiCombinationKeys = new ArrayList<>(afterMultiCombinationKey);
        ObjectForFileService forFileService = new ObjectForFileService();
        forFileService.setObject(multiCombinationKeys);
        try {
            FileService.saveObject(forFileService,"ReducedAfterMulti");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
