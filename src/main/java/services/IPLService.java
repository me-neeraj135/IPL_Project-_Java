package main.java.services;

import main.java.model.Delivery;
import main.java.model.Match;

import java.util.*;

public class IPLService {
    public Map<String, Integer> matchesPerYear(List<Match> matches) {
        Map<String, Integer> result = new HashMap<>();

        for (Match match : matches) {
            result.put(match.getSeason(), result.getOrDefault(match.getSeason(), 0) + 1);
        }
        return result;
    }

    public Map<String, Integer> matchesWonAllTeams(List<Match> matches) {
        Map<String, Integer> result = new HashMap<>();
        for (Match match : matches) {
            if (!match.getWinner().isEmpty()) {
                result.put(match.getWinner(), result.getOrDefault(match.getWinner(), 0) + 1);
            }
        }
        return result;
    }

    public Map<String, Integer> extraRunsPerTeam2016(List<Match> matches, List<Delivery> deliveries) {
        Set<String> matchIds2016 = new HashSet<>();
        for (Match match : matches) {
            String year = match.getSeason().length() >= 4 ? match.getSeason().substring(0, 4) : "";
            if (year.equals("2016")) {
                matchIds2016.add(match.getId());
            }
        }
        Map<String, Integer> extraRuns = new HashMap<>();
        for (Delivery delivery : deliveries) {
            if (matchIds2016.contains(delivery.getMatchId()) && !delivery.getBowlingTeam().isEmpty()) {
                extraRuns.put(delivery.getBowlingTeam(), extraRuns.getOrDefault(delivery.getBowlingTeam(), 0) + delivery.getExtraRuns());
            }
        }
        return extraRuns;
    }



    public Map<String, int[]> topEconomicalBowlers2015(List<Match> matches, List<Delivery> deliveries){
        Set<String>  matchId2015 = new HashSet<>();

        for (Match match: matches){
            String year = match.getSeason().length() >= 4 ? match.getSeason().substring(0,4):"";

            if(year.equals("2015")){
                matchId2015.add(match.getId());
            }
        }

        Map<String, int[]> bowlerStats  = new HashMap<>();
        for (Delivery delivery: deliveries){
            if(matchId2015.contains(delivery.getMatchId())){

                bowlerStats.putIfAbsent(delivery.getBowler(), new  int[]{0,0});

                int[] stats = bowlerStats.get(delivery.getBowler());
                stats[0] += delivery.getTotalRuns();

//                bowlerStats.get(delivery.getBowler())[0]+=delivery.getTotalRuns();
                if(delivery.getWideRuns()==0 && delivery.getNoballRuns()==0){
                    stats[1] += 1;
                }
            }
        }
        return bowlerStats;
    }


    public Map<String, String> topBatsmanPerYear(List<Match> matches, List<Delivery> deliveries) {
        Map<String, String> matchIdToYear = new HashMap<>();

        for (Match match : matches) {
            String seasonStr = match.getSeason();
            if (seasonStr != null && !seasonStr.trim().isEmpty()) {
                String trimmedSeason = seasonStr.trim();

                // Extract the 4-digit
                String year = trimmedSeason.length() >= 4 ? trimmedSeason.substring(0, 4) : "";

                if (!year.isEmpty()) {
                    matchIdToYear.put(match.getId().trim(), year);
                }
            }
        }

        Map<String, Integer> batsmanRunsPerYear = new HashMap<>();
        for (Delivery delivery : deliveries) {
            String deliveryMatchId = delivery.getMatchId();
            if (deliveryMatchId != null) {
                // Trim the ID for perfect mapping
                String year = matchIdToYear.get(deliveryMatchId.trim());
                String batsman = delivery.getBatsman();

                if (year != null && batsman != null && !batsman.trim().isEmpty()) {
                    String key = year + "_" + batsman.trim();

                    if (!batsmanRunsPerYear.containsKey(key)) {
                        batsmanRunsPerYear.put(key, delivery.getBatsmanRuns());
                    } else {
                        int existingRuns = batsmanRunsPerYear.get(key);
                        batsmanRunsPerYear.put(key, existingRuns + delivery.getBatsmanRuns());
                    }
                }
            }
        }

        Map<String, String> topPerYearMap = new TreeMap<>();
        Map<String, Integer> topRunsMap = new HashMap<>();

        for (Map.Entry<String, Integer> entry : batsmanRunsPerYear.entrySet()) {
            String[] parts = entry.getKey().split("_");
            String year = parts[0];
            String batsman = parts[1];
            int runs = entry.getValue();

            // Check if this batsman has the most runs for the year
            if (!topRunsMap.containsKey(year) || runs > topRunsMap.get(year)) {
                topRunsMap.put(year, runs);
                topPerYearMap.put(year, batsman + " | " + runs);
            }
        }

        return topPerYearMap;
    }

//this is map formator to print the data in a good formate
    public void printBowlerStats(Map<String, int[]> bowlerStats) {
        if (bowlerStats == null || bowlerStats.isEmpty()) {
            System.out.println("No data found to display.");
            return;
        }

        System.out.println("\n+------------------------------+---------+-------------+---------+");
        System.out.printf("| %-28s | %-7s | %-11s | %-7s |\n", "Bowler Name", "Runs", "Valid Balls", "Economy");
        System.out.println("+------------------------------+---------+-------------+---------+");

        for (Map.Entry<String, int[]> entry : bowlerStats.entrySet()) {
            String bowlerName = entry.getKey();
            int[] stats = entry.getValue();
            int runs = stats[0];
            int validBalls = stats[1];

            // Filter: Only include bowlers who bowled a minimum of 60 balls
            if (validBalls >= 60) {
                double economy = (double) runs / validBalls * 6;

                System.out.printf("| %-28s | %7d | %11d | %7.2f |\n",
                        bowlerName, runs, validBalls, economy);
            }
        }

        System.out.println("+------------------------------+---------+-------------+---------+");
    }

    public void printTopBatsmanPerYear(Map<String, String> topBatsmanMap) {
        if (topBatsmanMap == null || topBatsmanMap.isEmpty()) {
            System.out.println("No data available to print.");
            return;
        }

        System.out.println("\n+--------+------------------------------+------------+");
        System.out.printf("| %-6s | %-28s | %-10s |\n", "Year", "Top Batsman", "Total Runs");
        System.out.println("+--------+------------------------------+------------+");

        for (Map.Entry<String, String> entry : topBatsmanMap.entrySet()) {
            String year = entry.getKey();


            String[] data = entry.getValue().split(" \\| ");
            String batsman = data[0];
            int runs = Integer.parseInt(data[1]);

            System.out.printf("| %-6s | %-28s | %10d |\n", year, batsman, runs);
        }

        System.out.println("+--------+------------------------------+------------+");
    }



}
