import model.Delivery;
import model.Match;
import utils.CSVReader;
import utils.DataParser;
import services.IPLService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Match> matches = CSVReader.read("/home/nkr/Desktop/FileHandling/src/data/matches.csv", DataParser::toMatch);
        List<Delivery> deliveries = CSVReader.read("/home/nkr/Desktop/FileHandling/src/data/deliveries.csv", DataParser::toDelivery);


        IPLService service = new IPLService();
        Scanner sc = new Scanner(System.in);

        try {


            while (true) {
                System.out.println("=====  IPL Data =====");

                System.out.println("1- Number of matches played per year of all the years in IPL.");
                System.out.println("2- Number of matches won of all teams over all the years of IPL.");
                System.out.println("3- For the year 2016 get the extra runs conceded per team.");
                System.out.println("4- For the year 2015 get the top economical bowlers.");
                System.out.println("5- Top Batsman.");
                System.out.println("0- Exit.");
                System.out.println("\ninput your choice : ");




                int choice = sc.nextInt();


                switch (choice) {
                    case 1:
                        System.out.println(service.matchesPerYear(matches));
                        break;

                    case 2:
                        System.out.println(service.matchesWonAllTeams(matches));
                        break;

                    case 3:
                        System.out.println(service.extraRunsPerTeam2016(matches, deliveries));
                        break;
                    case 4:
                        Map<String, int[]> stats = service.topEconomicalBowlers2015(matches, deliveries);
                        service.printBowlerStats(stats);
                        break;
                    case 5:
                        Map<String, String> topBatsmanByYear = service.topBatsmanPerYear(matches, deliveries);


                        service.printTopBatsmanPerYear(topBatsmanByYear);
                        break;

                    case 0:
                        System.out.println("you are exit now");
                        return;

                    default:
                        System.out.println("\nInvalid input try another!");

                }


            }


        } catch (Exception e) {
            System.out.println("Error :  " + e.getMessage());
        }


    }
}


