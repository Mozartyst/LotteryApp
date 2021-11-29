package update;

import dataSupport.FileService;
import downloader.LotteryDrawsJSONDownloader;
import downloader.LotteryDrawsXMLDownloader;
import entity.OneDraw;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Properties;

public class LotteryUpdate {
    public void run(Properties lotteriesProp) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Properties properties = new Properties();
        ArrayList<OneDraw> lotteryNumbers;
        LocalDateTime lastDrawDate;

        properties.load(new FileInputStream(lotteriesProp.getProperty("irishLotto")));
        lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        lastDrawDate = lotteryNumbers.get(lotteryNumbers.size() - 1).getDrawDate();
        if (lastDrawDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
            if (setNextTime(lastDrawDate, 3).isBefore(localDateTime)) {
                new LotteryDrawsXMLDownloader(properties);
            }
        } else if (lastDrawDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            if (setNextTime(lastDrawDate, 4).isBefore(localDateTime)) {
                new LotteryDrawsXMLDownloader(properties);
            }
        }
        lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        lastDrawDate = lotteryNumbers.get(lotteryNumbers.size() - 1).getDrawDate();
        printDate("Irish ", lastDrawDate);

        properties.load(new FileInputStream(lotteriesProp.getProperty("euroLotto")));
        lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        lastDrawDate = lotteryNumbers.get(lotteryNumbers.size() - 1).getDrawDate();
        if (lastDrawDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
            if (setNextTime(lastDrawDate, 3).isBefore(localDateTime)) {
                new LotteryDrawsXMLDownloader(properties);
            }
        } else if (lastDrawDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            if (setNextTime(lastDrawDate, 4).isBefore(localDateTime)) {
                new LotteryDrawsXMLDownloader(properties);
            }
        }
        lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        lastDrawDate = lotteryNumbers.get(lotteryNumbers.size() - 1).getDrawDate();
        printDate("Euro ", lastDrawDate);

        properties.load(new FileInputStream(lotteriesProp.getProperty("polishLotto")));
        lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        lastDrawDate = lotteryNumbers.get(lotteryNumbers.size() - 1).getDrawDate();
        if (lastDrawDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
            if (setNextTime(lastDrawDate, 2).isBefore(localDateTime)) {
                new LotteryDrawsJSONDownloader(properties);
            }
        } else if (lastDrawDate.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
            if (setNextTime(lastDrawDate, 2).isBefore(localDateTime)) {
                new LotteryDrawsJSONDownloader(properties);
            }
        } else if (lastDrawDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            if (setNextTime(lastDrawDate, 3).isBefore(localDateTime)) {
                new LotteryDrawsJSONDownloader(properties);
            }
        }
        lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        lastDrawDate = lotteryNumbers.get(lotteryNumbers.size() - 1).getDrawDate();
        printDate("Polish ", lastDrawDate);
    }

    private LocalDateTime setNextTime(LocalDateTime localDateTime, int days) {
        long oneDay = 86400000L * days;

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(LocalDateTime.of(localDateTime.getYear()
                , localDateTime.getMonth()
                , localDateTime.getDayOfMonth()
                , localDateTime.getHour()
                , localDateTime.getMinute()).toInstant(ZoneOffset.UTC).toEpochMilli() + oneDay), ZoneOffset.UTC);
    }
    private void printDate(String lotto, LocalDateTime lastUpdateDate){
        System.out.println(lotto +"last update " + lastUpdateDate);
    }
}

