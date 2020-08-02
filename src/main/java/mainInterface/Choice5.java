package mainInterface;

import dataSupport.MultiCombinationReducer;

import java.io.IOException;

public class Choice5 {
    public void run(){
        try {
            new MultiCombinationReducer().reduceMultiFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
