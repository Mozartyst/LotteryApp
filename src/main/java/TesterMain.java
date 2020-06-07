import algorithm.Algorithm;
import dataSupport.FileService;
import support.Settings;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;

public class TesterMain {
    private static ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("FullLotteryNumbersFile");

    public static void main(String[] args) {
//        for (int i = 20; i >= 1; i--) {
//            ArrayList<Integer> proposition = new Algorithm().getPropositionList(i);
//            System.out.println(proposition);
//            System.out.println(lotteryNumbers.get(i - 1));
//        }
        LocalDateTime localDateTime1 = LocalDateTime.of(2020,06,6,20,30,00,000);
        long l1 = localDateTime1.toInstant(ZoneOffset.UTC).toEpochMilli();
        LocalDateTime localDateTime2 = LocalDateTime.of(2020,06,10,20,30,00,000);
        long l2 = localDateTime2.toInstant(ZoneOffset.UTC).toEpochMilli();
        LocalDateTime localDateTime3 = LocalDateTime.of(2020,06,13,20,30,00,000);
        long l3 = localDateTime3.toInstant(ZoneOffset.UTC).toEpochMilli();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(l3),ZoneOffset.UTC.normalized());
        Settings settings = new Settings(l1,l2);
        try {
            FileService.saveObject(settings,"Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

