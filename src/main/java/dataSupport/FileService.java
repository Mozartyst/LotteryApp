package dataSupport;

import entity.ObjectForFileService;

import java.io.*;

public class FileService {

    public static <R> void saveObject(R object, String fileName) throws IOException {
        ObjectForFileService<R> objectForFileService = new ObjectForFileService<>(object);
        File file = new File("src/main/resources/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(objectForFileService);
        outputStream.flush();
        outputStream.close();
    }

    public static <R> R loadObject(String fileName) {
        ObjectInputStream inputStream = null;
        ObjectForFileService<R> objectForFileService = null;
        R object = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("src/main/resources/" + fileName));
            objectForFileService = (ObjectForFileService<R>) inputStream.readObject();
            object = objectForFileService.getObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
