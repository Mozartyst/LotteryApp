package dataSupport;

import entity.ObjectForFileService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {


    public static ArrayList<ArrayList<Integer>> loadFile(String fileName) {
        Path path = Paths.get("src/main/resources/" + fileName);
        ArrayList<ArrayList<Integer>> lotteryNumbers = new ArrayList<>();
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        try {
            List<String> lottery = Files.readAllLines(path, StandardCharsets.UTF_8);
            int counter = 0;
            for (String s : lottery) {
                counter++;
                integerArrayList.add(Integer.valueOf(s));
                if (counter == 6) {
                    counter = 0;
                    lotteryNumbers.add(integerArrayList);
                    integerArrayList = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lotteryNumbers;
    }

    public static void saveFile(ArrayList<ArrayList<Integer>> lotteryNumbers, String fileName) {
        Path path = Paths.get("src/main/resources/" + fileName);
        List<String> list1 = new ArrayList<>();
        for (ArrayList<Integer> list : lotteryNumbers) {
            list.forEach((x) -> list1.add(String.valueOf(x)));
        }
        try {
            Files.write(path, list1, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveObject(ObjectForFileService objectForFileService, String fileName) throws IOException {
        File file = new File("src/main/resources/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(objectForFileService);
        outputStream.flush();
        outputStream.close();
    }

    public static ObjectForFileService loadObject(String fileName) {
        ObjectInputStream inputStream = null;
        ObjectForFileService objectForFileService = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("src/main/resources/" + fileName));
            objectForFileService = (ObjectForFileService) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objectForFileService;
    }
}
