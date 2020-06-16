import dataSupport.FileService;
import entity.MultiCombinationKeys;

import java.io.IOException;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<MultiCombinationKeys> afterMultiCombinationKey = FileService.loadObject("ReducedMulti");
        System.out.println(afterMultiCombinationKey);
    }
}
