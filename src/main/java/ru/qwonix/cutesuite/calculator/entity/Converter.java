package ru.qwonix.cutesuite.calculator.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Converter {
    public static void readFromJSON(String path) {
        String s;
        try {
            s = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            writeToJSON(path);
            return;
        }

        JSONObject jsonObject = new JSONObject(s);

        JSONObject jsonGuest = jsonObject.getJSONObject("Guest");
        JSONArray guestTypesJson = jsonGuest.names();
        for (int i = 0; i < guestTypesJson.length(); i++) {
            String guestType = guestTypesJson.getString(i);
            JSONObject guest = jsonGuest.getJSONObject(guestType);

            Guest.valueOf(guestType).cost = guest.getInt("cost");
            Guest.valueOf(guestType).percent = guest.getInt("percent");
        }

        JSONObject jsonDay = jsonObject.getJSONObject("Day");
        JSONArray daysTypesJson = jsonDay.names();
        for (int i = 0; i < daysTypesJson.length(); i++) {
            String dayType = daysTypesJson.getString(i);
            JSONObject day = jsonDay.getJSONObject(dayType);

            Day.valueOf(dayType).setCount(day.getInt("count"));
            Day.valueOf(dayType).setPercent(day.getInt("percent"));
        }
    }

    public static void writeToJSON(String path) {
        Map<String, JSONObject> guests = Arrays.stream(Guest.values())
                .collect(Collectors.toMap(Enum::name, guest -> new JSONObject()
                                .put("cost", guest.getCost())
                                .put("percent", guest.getPercent())
                        )
                );

        Map<String, JSONObject> days = Arrays.stream(Day.values())
                .collect(Collectors.toMap(Enum::name, day -> new JSONObject()
                                .put("count", day.getCount())
                                .put("percent", day.getPercent())
                        )
                );

        JSONObject jsonObject = new JSONObject()
                .put("Guest", guests)
                .put("Day", days);

        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(jsonObject.toString(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
