package update;

import dataSupport.FileService;
import downloader.LotteryDownloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Properties;

public class LotteryUpdate {
    public void run() throws IOException, ClassNotFoundException {
        UpdateSettings updateSettings = FileService.loadObject("Settings");
        LocalDateTime localDateTime = LocalDateTime.now();

        if (updateSettings.getIrishNextUpdate().compareTo(localDateTime) < 0) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/IrishLotto"));
            FileService.saveObject(new LotteryDownloader().getNumbers(2016, 2020, properties), "IrishLottery/FullIrishNumbersFile");
            if (localDateTime.getDayOfWeek() == DayOfWeek.MONDAY) {
                setIrishLastUpdate(updateSettings, setLastTime(localDateTime, 2));
                setIrishNextUpdate(updateSettings, setNextTime(updateSettings.getIrishLastUpdate(), 4));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.TUESDAY) {
                setIrishLastUpdate(updateSettings, setLastTime(localDateTime, 3));
                setIrishNextUpdate(updateSettings, setNextTime(updateSettings.getIrishLastUpdate(), 4));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                setIrishLastUpdate(updateSettings, setLastTime(localDateTime, 0));
                setIrishNextUpdate(updateSettings, setNextTime(updateSettings.getIrishLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.THURSDAY) {
                setIrishLastUpdate(updateSettings, setLastTime(localDateTime, 1));
                setIrishNextUpdate(updateSettings, setNextTime(updateSettings.getIrishLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.FRIDAY) {
                setIrishLastUpdate(updateSettings, setLastTime(localDateTime, 2));
                setIrishNextUpdate(updateSettings, setNextTime(updateSettings.getIrishLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.SATURDAY) {
                setIrishLastUpdate(updateSettings, setLastTime(localDateTime, 0));
                setIrishNextUpdate(updateSettings, setNextTime(updateSettings.getIrishLastUpdate(), 4));
            } else {
                setIrishLastUpdate(updateSettings, setLastTime(localDateTime, 1));
                setIrishNextUpdate(updateSettings, setNextTime(updateSettings.getIrishLastUpdate(), 4));
            }
        }

        if (updateSettings.getEuroNextUpdate().compareTo(localDateTime) < 0) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/EuroLotto"));
            FileService.saveObject(new LotteryDownloader().getNumbers(2015, 2020, properties), "EuroLottery/FullEuroNumbersFile");
            if (localDateTime.getDayOfWeek() == DayOfWeek.MONDAY) {
                setEuroLastUpdate(updateSettings, setLastTime(localDateTime, 3));
                setEuroNextUpdate(updateSettings, setNextTime(updateSettings.getEuroLastUpdate(), 4));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.TUESDAY) {
                setEuroLastUpdate(updateSettings, setLastTime(localDateTime, 0));
                setEuroNextUpdate(updateSettings, setNextTime(updateSettings.getEuroLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                setEuroLastUpdate(updateSettings, setLastTime(localDateTime, 1));
                setEuroNextUpdate(updateSettings, setNextTime(updateSettings.getEuroLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.THURSDAY) {
                setEuroLastUpdate(updateSettings, setLastTime(localDateTime, 2));
                setEuroNextUpdate(updateSettings, setNextTime(updateSettings.getEuroLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.FRIDAY) {
                setEuroLastUpdate(updateSettings, setLastTime(localDateTime, 0));
                setEuroNextUpdate(updateSettings, setNextTime(updateSettings.getEuroLastUpdate(), 4));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.SATURDAY) {
                setEuroLastUpdate(updateSettings, setLastTime(localDateTime, 1));
                setEuroNextUpdate(updateSettings, setNextTime(updateSettings.getEuroLastUpdate(), 4));
            } else {
                setEuroLastUpdate(updateSettings, setLastTime(localDateTime, 2));
                setEuroNextUpdate(updateSettings, setNextTime(updateSettings.getEuroLastUpdate(), 4));
            }
        }
        if (updateSettings.getPolishNextUpdate().compareTo(localDateTime) < 0) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/PolishLotto"));
            FileService.saveObject(new LotteryDownloader().getNumbers(2015, 2020,properties), "PolishLottery/FullPolishNumbersFile");
            if (localDateTime.getDayOfWeek() == DayOfWeek.MONDAY) {
                setPolishLastUpdate(updateSettings, setLastTime(localDateTime, 2));
                setPolishNextUpdate(updateSettings, setNextTime(updateSettings.getPolishLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.TUESDAY) {
                setPolishLastUpdate(updateSettings, setLastTime(localDateTime, 0));
                setPolishNextUpdate(updateSettings, setNextTime(updateSettings.getPolishLastUpdate(), 2));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                setPolishLastUpdate(updateSettings, setLastTime(localDateTime, 1));
                setPolishNextUpdate(updateSettings, setNextTime(updateSettings.getPolishLastUpdate(), 2));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.THURSDAY) {
                setPolishLastUpdate(updateSettings, setLastTime(localDateTime, 0));
                setPolishNextUpdate(updateSettings, setNextTime(updateSettings.getPolishLastUpdate(), 2));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.FRIDAY) {
                setPolishLastUpdate(updateSettings, setLastTime(localDateTime, 1));
                setPolishNextUpdate(updateSettings, setNextTime(updateSettings.getPolishLastUpdate(), 2));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.SATURDAY) {
                setPolishLastUpdate(updateSettings, setLastTime(localDateTime, 0));
                setPolishNextUpdate(updateSettings, setNextTime(updateSettings.getPolishLastUpdate(), 3));
            } else {
                setPolishLastUpdate(updateSettings, setLastTime(localDateTime, 1));
                setPolishNextUpdate(updateSettings, setNextTime(updateSettings.getPolishLastUpdate(), 3));
            }
        }
        System.out.println("Last IrishLottery update: " + updateSettings.getIrishLastUpdate());
        System.out.println("Last EuroMillion update: " + updateSettings.getEuroLastUpdate());
        System.out.println("Last PolishLottery update: " + updateSettings.getPolishLastUpdate());
        FileService.saveObject(updateSettings, "Settings");
    }

    private LocalDateTime setLastTime(LocalDateTime localDateTime, int days) {
        long oneDay = 86400000L * days;
        LocalDateTime updatedTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(LocalDateTime.of(localDateTime.getYear()
                , localDateTime.getMonth()
                , localDateTime.getDayOfMonth()
                , 20
                , 30).toInstant(ZoneOffset.UTC).toEpochMilli() - oneDay), ZoneOffset.UTC);

        return updatedTime;
    }

    private LocalDateTime setNextTime(LocalDateTime localDateTime, int days) {
        long oneDay = 86400000L * days;
        LocalDateTime updatedTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(LocalDateTime.of(localDateTime.getYear()
                , localDateTime.getMonth()
                , localDateTime.getDayOfMonth()
                , 20
                , 30).toInstant(ZoneOffset.UTC).toEpochMilli() + oneDay), ZoneOffset.UTC);

        return updatedTime;
    }

    private void setIrishLastUpdate(UpdateSettings updateSettings, LocalDateTime localDateTime) {
        updateSettings.setIrishLastUpdate(localDateTime);
    }

    private void setEuroLastUpdate(UpdateSettings updateSettings, LocalDateTime localDateTime) {
        updateSettings.setEuroLastUpdate(localDateTime);
    }

    private void setPolishLastUpdate(UpdateSettings updateSettings, LocalDateTime localDateTime) {
        updateSettings.setPolishLastUpdate(localDateTime);
    }

    private void setIrishNextUpdate(UpdateSettings updateSettings, LocalDateTime localDateTime) {
        updateSettings.setIrishNextUpdate(localDateTime);
    }

    private void setEuroNextUpdate(UpdateSettings updateSettings, LocalDateTime localDateTime) {
        updateSettings.setEuroNextUpdate(localDateTime);
    }

    private void setPolishNextUpdate(UpdateSettings updateSettings, LocalDateTime localDateTime) {
        updateSettings.setPolishNextUpdate(localDateTime);
    }

}

