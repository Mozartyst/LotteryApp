package dataSupport;

import downloader.LotteryDownloader;
import support.Settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Properties;

public class LotteryUpdate {
    public void run() throws IOException, ClassNotFoundException {
        Settings settings = FileService.loadObject("Settings");
        LocalDateTime localDateTime = LocalDateTime.now();

        if (settings.getIrishNextUpdate().compareTo(localDateTime) < 0) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/IrishLotto"));
            FileService.saveObject(new LotteryDownloader().getNumbers(2016, 2020, properties), "IrishLottery/FullIrishNumbersFile");
            if (localDateTime.getDayOfWeek() == DayOfWeek.MONDAY) {
                setIrishLastUpdate(settings, setLastTime(localDateTime, 2));
                setIrishNextUpdate(settings, setNextTime(settings.getIrishLastUpdate(), 4));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.TUESDAY) {
                setIrishLastUpdate(settings, setLastTime(localDateTime, 3));
                setIrishNextUpdate(settings, setNextTime(settings.getIrishLastUpdate(), 4));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                setIrishLastUpdate(settings, setLastTime(localDateTime, 0));
                setIrishNextUpdate(settings, setNextTime(settings.getIrishLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.THURSDAY) {
                setIrishLastUpdate(settings, setLastTime(localDateTime, 1));
                setIrishNextUpdate(settings, setNextTime(settings.getIrishLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.FRIDAY) {
                setIrishLastUpdate(settings, setLastTime(localDateTime, 2));
                setIrishNextUpdate(settings, setNextTime(settings.getIrishLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.SATURDAY) {
                setIrishLastUpdate(settings, setLastTime(localDateTime, 0));
                setIrishNextUpdate(settings, setNextTime(settings.getIrishLastUpdate(), 4));
            } else {
                setIrishLastUpdate(settings, setLastTime(localDateTime, 1));
                setIrishNextUpdate(settings, setNextTime(settings.getIrishLastUpdate(), 4));
            }
        }

        if (settings.getEuroNextUpdate().compareTo(localDateTime) < 0) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/EuroLotto"));
            FileService.saveObject(new LotteryDownloader().getNumbers(2015, 2020, properties), "EuroLottery/FullEuroNumbersFile");
            if (localDateTime.getDayOfWeek() == DayOfWeek.MONDAY) {
                setEuroLastUpdate(settings, setLastTime(localDateTime, 3));
                setEuroNextUpdate(settings, setNextTime(settings.getEuroLastUpdate(), 4));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.TUESDAY) {
                setEuroLastUpdate(settings, setLastTime(localDateTime, 0));
                setEuroNextUpdate(settings, setNextTime(settings.getEuroLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                setEuroLastUpdate(settings, setLastTime(localDateTime, 1));
                setEuroNextUpdate(settings, setNextTime(settings.getEuroLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.THURSDAY) {
                setEuroLastUpdate(settings, setLastTime(localDateTime, 2));
                setEuroNextUpdate(settings, setNextTime(settings.getEuroLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.FRIDAY) {
                setEuroLastUpdate(settings, setLastTime(localDateTime, 0));
                setEuroNextUpdate(settings, setNextTime(settings.getEuroLastUpdate(), 4));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.SATURDAY) {
                setEuroLastUpdate(settings, setLastTime(localDateTime, 1));
                setEuroNextUpdate(settings, setNextTime(settings.getEuroLastUpdate(), 4));
            } else {
                setEuroLastUpdate(settings, setLastTime(localDateTime, 2));
                setEuroNextUpdate(settings, setNextTime(settings.getEuroLastUpdate(), 4));
            }
        }
        if (settings.getPolishNextUpdate().compareTo(localDateTime) < 0) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/PolishLotto"));
            FileService.saveObject(new LotteryDownloader().getNumbers(2015, 2020,properties), "PolishLottery/FullPolishNumbersFile");
            if (localDateTime.getDayOfWeek() == DayOfWeek.MONDAY) {
                setPolishLastUpdate(settings, setLastTime(localDateTime, 2));
                setPolishNextUpdate(settings, setNextTime(settings.getPolishLastUpdate(), 3));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.TUESDAY) {
                setPolishLastUpdate(settings, setLastTime(localDateTime, 0));
                setPolishNextUpdate(settings, setNextTime(settings.getPolishLastUpdate(), 2));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                setPolishLastUpdate(settings, setLastTime(localDateTime, 1));
                setPolishNextUpdate(settings, setNextTime(settings.getPolishLastUpdate(), 2));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.THURSDAY) {
                setPolishLastUpdate(settings, setLastTime(localDateTime, 0));
                setPolishNextUpdate(settings, setNextTime(settings.getPolishLastUpdate(), 2));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.FRIDAY) {
                setPolishLastUpdate(settings, setLastTime(localDateTime, 1));
                setPolishNextUpdate(settings, setNextTime(settings.getPolishLastUpdate(), 2));
            } else if (localDateTime.getDayOfWeek() == DayOfWeek.SATURDAY) {
                setPolishLastUpdate(settings, setLastTime(localDateTime, 0));
                setPolishNextUpdate(settings, setNextTime(settings.getPolishLastUpdate(), 3));
            } else {
                setPolishLastUpdate(settings, setLastTime(localDateTime, 1));
                setPolishNextUpdate(settings, setNextTime(settings.getPolishLastUpdate(), 3));
            }
        }
        System.out.println("Last IrishLottery update: " + settings.getIrishLastUpdate());
        System.out.println("Last EuroMillion update: " + settings.getEuroLastUpdate());
        System.out.println("Last PolishLottery update: " + settings.getPolishLastUpdate());
        FileService.saveObject(settings, "Settings");
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

    private void setIrishLastUpdate(Settings settings, LocalDateTime localDateTime) {
        settings.setIrishLastUpdate(localDateTime);
    }

    private void setEuroLastUpdate(Settings settings, LocalDateTime localDateTime) {
        settings.setEuroLastUpdate(localDateTime);
    }

    private void setPolishLastUpdate(Settings settings, LocalDateTime localDateTime) {
        settings.setPolishLastUpdate(localDateTime);
    }

    private void setIrishNextUpdate(Settings settings, LocalDateTime localDateTime) {
        settings.setIrishNextUpdate(localDateTime);
    }

    private void setEuroNextUpdate(Settings settings, LocalDateTime localDateTime) {
        settings.setEuroNextUpdate(localDateTime);
    }

    private void setPolishNextUpdate(Settings settings, LocalDateTime localDateTime) {
        settings.setPolishNextUpdate(localDateTime);
    }

}

