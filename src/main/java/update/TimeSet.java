package update;

import dataSupport.FileService;

import java.io.IOException;
import java.time.LocalDateTime;

public class TimeSet {
    public void setNewDate() throws IOException {

        LocalDateTime euroLast = LocalDateTime.of(2020, 8, 7, 20, 30);
        LocalDateTime euroNext = LocalDateTime.of(2020, 8, 12, 20, 30);
        LocalDateTime lottoLast = LocalDateTime.of(2020, 8, 5, 20, 30);
        LocalDateTime lottoNext = LocalDateTime.of(2020, 8, 8, 20, 30);
        LocalDateTime polishLast = LocalDateTime.of(2020, 8, 6, 20, 30);
        LocalDateTime polishNext = LocalDateTime.of(2020, 8, 8, 20, 30);


        UpdateSettings updateSettings = new UpdateSettings(lottoLast, lottoNext, euroLast, euroNext,polishLast,polishNext);
        FileService.saveObject(updateSettings, "Settings");
    }
}
