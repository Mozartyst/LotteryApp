package dataSupport;

import entity.ObjectForFileService;

import java.io.*;

public class FileService {

    public static <R> void saveObject(R object, String fileName) throws IOException {
        ObjectForFileService<R> objectForFileService = new ObjectForFileService<>(object);
        File file = new File("src/main/resources/" + fileName);
        boolean isFileExist = file.exists();
        if (!isFileExist) {
            isFileExist = file.createNewFile();
        }
        if (isFileExist) {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            try {
                outputStream.writeObject(objectForFileService);
            } finally {
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    public static <R> R loadObject(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = null;
        ObjectForFileService<R> objectForFileService;
        R object;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("src/main/resources/" + fileName));
            objectForFileService = (ObjectForFileService<R>) inputStream.readObject();
            object = objectForFileService.getObject();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return object;
    }

    public static boolean isFile(String fileName) {
        File file = new File("src/main/resources/" + fileName);
        return file.exists();
    }
}
