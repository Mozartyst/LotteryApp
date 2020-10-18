package update;

import dataSupport.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class UpdateDrawnNumbers {
    private final Properties properties;

    public UpdateDrawnNumbers(Properties properties){
        this.properties = properties;
    }

    public void update() throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> pastDrawnNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));

    }
}
