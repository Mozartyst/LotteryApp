package mainInterface;

import threeHunter.MultiThreesCreator;

import java.io.IOException;
import java.util.Properties;

public class Choice6 {
    public void run(Properties properties) throws IOException, ClassNotFoundException {
        new MultiThreesCreator().create(properties);
    }
}
