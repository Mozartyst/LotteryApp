package downloader;

import dataSupport.FileService;
import entity.OneDraw;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DownloadPolish {
    public DownloadPolish(Properties properties) throws IOException {
        ArrayList<OneDraw> lotteryNumbers = new ArrayList<>();
        URL url = new URL("https://www.lotto.pl/api/lotteries/draw-results/by-gametype?game=Lotto&index=0&size=500&sort=drawDate&order=DESC");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        JsonParser jsonParser = Json.createParser(httpURLConnection.getInputStream());
        JsonParser.Event event = jsonParser.next();
        String name = event.name();
        if (name.contains("START_OBJECT")) {
            JsonObject object = jsonParser.getObject();
            for (int i = 499; i >= 0; i--) {
                OneDraw oneDraw = new OneDraw();
                JsonObject drawObject = object.getJsonArray("items")
                        .getJsonObject(i)
                        .getJsonArray("results")
                        .getJsonObject(0);

                oneDraw.setDrawDate(LocalDateTime.parse(drawObject.get("drawDate").toString()
                        .replace("\"", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
                oneDraw.setDrawNumber(Integer.valueOf(drawObject.get("drawSystemId").toString()));

                List<String> strings = Arrays.asList(drawObject.get("resultsJson").toString()
                        .replace("[", "")
                        .replace("]", "")
                        .split(","));
                ArrayList<Integer> weekList = new ArrayList<>();
                strings.forEach((s -> weekList.add(Integer.valueOf(s))));
                oneDraw.setDrawNumbers(weekList);
                lotteryNumbers.add(oneDraw);
            }
            FileService.saveObject(lotteryNumbers, properties.getProperty("lotteryNumbers"));
        }
    }
}
