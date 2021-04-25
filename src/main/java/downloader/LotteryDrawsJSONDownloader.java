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
import java.util.*;

public class LotteryDrawsJSONDownloader {
    public LotteryDrawsJSONDownloader(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        URL url = new URL(properties.getProperty("url"));
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        JsonParser jsonParser = Json.createParser(httpURLConnection.getInputStream());
        JsonParser.Event event = jsonParser.next();
        String name = event.name();
        if (name.contains("START_OBJECT")) {
            JsonObject object = jsonParser.getObject();
            Integer latestDrawNumber = Integer.valueOf(object.getJsonArray("items")
                    .getJsonObject(0)
                    .getJsonArray("results")
                    .getJsonObject(0)
                    .get("drawSystemId").toString());
            Integer lastDrawNumber = lotteryNumbers.get(lotteryNumbers.size() - 1).getDrawNumber();
            int difference = latestDrawNumber - lastDrawNumber;
            if (difference > 0) {
                for (int i = difference - 1; i >= 0; i--) {
                    OneDraw oneDraw = new OneDraw();
                    JsonObject drawObject = object.getJsonArray("items")
                            .getJsonObject(i)
                            .getJsonArray("results")
                            .getJsonObject(0);

                    oneDraw.setDrawDate(LocalDateTime.parse(drawObject.get("drawDate").toString()
                            .replace("\"", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
                    oneDraw.setDrawNumber(Integer.valueOf(drawObject.get("drawSystemId").toString()));

                    List<String> resultsJson = Arrays.asList(drawObject.get("resultsJson").toString()
                            .replace("[", "")
                            .replace("]", "")
                            .split(","));
                    ArrayList<Integer> results = new ArrayList<>();
                    resultsJson.forEach((s -> results.add(Integer.valueOf(s))));
                    Collections.sort(results);
                    oneDraw.setDrawNumbers(results);
                    lotteryNumbers.add(oneDraw);
                }
                FileService.saveObject(lotteryNumbers,properties.getProperty("lotteryNumbers"));
            }
        }
    }
}
