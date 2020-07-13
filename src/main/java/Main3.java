import dataSupport.FileService;
import support.Settings;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Main3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        long euroLast = LocalDateTime.of(2020, 7, 7, 20, 30).toInstant(ZoneOffset.UTC).toEpochMilli();
        long euroNext = LocalDateTime.of(2020, 7, 10, 20, 30).toInstant(ZoneOffset.UTC).toEpochMilli();
        long lottoLast = LocalDateTime.of(2020, 7, 8, 20, 30).toInstant(ZoneOffset.UTC).toEpochMilli();
        long lottoNext = LocalDateTime.of(2020, 7, 11, 20, 30).toInstant(ZoneOffset.UTC).toEpochMilli();
        Settings settings = new Settings(lottoLast,lottoNext,euroLast,euroNext);
        FileService.saveObject(settings,"Settings");
    }
}
