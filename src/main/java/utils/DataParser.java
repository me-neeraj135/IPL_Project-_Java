package main.java.utils;

import main.java.model.Delivery;
import main.java.model.Match;

import java.util.Map;

public class DataParser {

    public static Match toMatch(Map<String, String> row) {
        return new Match(
                row.getOrDefault("id", "").trim(),
                row.getOrDefault("season", row.getOrDefault("date", "")).trim(),
                row.getOrDefault("winner", "").trim()
        );
    }

    public static Delivery toDelivery(Map<String, String> row) throws RuntimeException {

        try {
            return new Delivery(


                    row.get("match_id"),
                    row.get("bowling_team"),
                    row.get("bowler"),
                    Integer.parseInt(row.getOrDefault("extra_runs", "0")),
                    Integer.parseInt(row.getOrDefault("total_runs", "0")),
                    Integer.parseInt(row.getOrDefault("wide_runs", "0")),
                    Integer.parseInt(row.getOrDefault("noball_runs", "0")),
                    row.getOrDefault("batsman", "").trim(),
                    Integer.parseInt(row.getOrDefault("batsman_runs", "0"))








                    );
        } catch (Exception e) {
            throw new RuntimeException("Error  in  parsing Delivery  row", e);
        }

    }
}


