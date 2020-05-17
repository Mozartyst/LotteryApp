package dataSupport;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotteryNumbers {
    public List<String> readFileAndAddToList(String fileName)
    {
        List<String> lines = Collections.emptyList();
        try
        {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            // do something
            System.out.println("File does not exist...");
            e.printStackTrace();
        }
        return lines;
    }


    public ArrayList<ArrayList<Integer>> convertToInt(ArrayList<String> lista) {
        ArrayList<ArrayList<Integer>> intLista = new ArrayList<>();

        for(String s : lista) {
            ArrayList<Integer> intList = new ArrayList<>();
            intList.clear();
            s.trim();
            String[] aa = s.split(" ", -2);
            for (int b = 0; b <= aa.length - 1; b++){
                intList.add(Integer.valueOf(aa[b]));
            }
            Collections.sort(intList);
            intLista.add(intList);

        }

        Collections.reverse(intLista);
        return intLista;
    }
}
