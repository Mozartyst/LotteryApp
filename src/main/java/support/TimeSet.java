package support;

import dataSupport.FileService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeSet {
    public void setNewDate() throws IOException {

        LocalDateTime euroLast = LocalDateTime.of(2020, 7, 28, 20, 30);
        LocalDateTime euroNext = LocalDateTime.of(2020, 7, 31, 20, 30);
        LocalDateTime lottoLast = LocalDateTime.of(2020, 7, 29, 20, 30);
        LocalDateTime lottoNext = LocalDateTime.of(2020, 8, 1, 20, 30);
        LocalDateTime polishLast = LocalDateTime.of(2020, 7, 28, 20, 30);
        LocalDateTime polishNext = LocalDateTime.of(2020, 7, 30, 20, 30);


        Settings settings = new Settings(lottoLast, lottoNext, euroLast, euroNext,polishLast,polishNext);
        FileService.saveObject(settings, "Settings");
    }
}
