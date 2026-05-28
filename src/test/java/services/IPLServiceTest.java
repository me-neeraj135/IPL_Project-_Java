package services;

import main.java.model.Delivery;
import main.java.model.Match;
import main.java.services.IPLService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IPLServiceTest {


    private static IPLService iplService;
    private List<Match> sharedMatches;
    private List<Delivery> sharedDeliveries;


    // Run once when the class loads to prepare heavy resources

    @BeforeAll
    static void initialTestSuite() {
        iplService = new IPLService();
        System.out.println("--- Starting IPL Service Test Suite ---");
    }

    // Run before every single test method to provide fresh mock data
    @BeforeEach
    void setUpMocData() {
        System.out.println("Initializing test mock data configurations...");


        sharedMatches = List.of(
                new Match("1", "2016", "CSK"),
                new Match("2", "2016", "KKR"),
                new Match("3", "2017", "MI"),
                new Match("10", "2015", "CSK"));

        sharedDeliveries = List.of(
                new Delivery("1", "CSK", "Bowler1", 2, 0, 0, 0, "Virat", 25),
                new Delivery("1", "CSK", "Bowler1", 3, 0, 0, 0, "Virat", 30),
                new Delivery("1", "CSK", "Bowler2", 0, 0, 0, 0, "Rohit", 10),
                new Delivery("2", "KKR", "Bowler3", 10, 0, 0, 0, "Rohit", 15),
                new Delivery("2", "KKR", "Bowler3", 0, 0, 0, 0, "Rohit", 20),
                new Delivery("10", "CSK", "BowlerA", 0, 5, 0, 0, "Batsman1", 1),
                new Delivery("10", "CSK", "BowlerA", 0, 1, 0, 0, "Batsman2", 0),
                new Delivery("10", "CSK", "BowlerB", 0, 3, 1, 0, "Batsman3", 0));
    }

    //test matches per year
    @Test
    void matchesPerYearTest() {


        Map<String, Integer> actualResult = iplService.matchesPerYear(sharedMatches);

        assertEquals(2, actualResult.get("2016"));
        assertEquals(1, actualResult.get("2017"));

    }

    //  Test  matchesWonAllTeams_shouldCountWinsPerTeam
    @Test
    void matchesWonAllTeamsTest() {


        Map<String, Integer> actualResult = iplService.matchesWonAllTeams(sharedMatches);

        assertEquals(2, actualResult.get("CSK"));
        assertNull(actualResult.get("RCB"));

    }


    //    extraRunsPerTeam2016_sumExtraRunsFor2016MatchesOnly
    @Test
    void extraRunsPerTeam2016Test() {


        Map<String, Integer> actualResult = iplService.extraRunsPerTeam2016(sharedMatches, sharedDeliveries);
        assertEquals(5, actualResult.get("CSK"));
        assertNull(actualResult.get("RCB"));
    }

    //topEconomicalBowlers2015_TrackRunsAndValidBalls
    @Test
    void topEconomicalBowlers2015() {


        Map<String, int[]> actualResult = iplService.topEconomicalBowlers2015(sharedMatches, sharedDeliveries);

        assertEquals(6, actualResult.get("BowlerA")[0]);
        assertEquals(2, actualResult.get("BowlerA")[1]);
        assertEquals(3, actualResult.get("BowlerB")[0]);
    }

    //    topBatsmanPerYear_ReturnHighestRunScorerForEachSeason
    @Test
    void topBatsmanPerYear() {

        Map<String, String> actualResult = iplService.topBatsmanPerYear(sharedMatches, sharedDeliveries);


        assertEquals("Virat | 55", actualResult.get("2016"));
//        assertEquals("Rohit | 35", actualResult.get("2017"));
    }

    // Required edge case: Handle empty context safely
    @Test
    void matchesPerYear_shouldReturnEmptyMapWhenInputIsEmpty() {
        Map<String, Integer> result = iplService.matchesPerYear(List.of());
        assertTrue(result.isEmpty());
    }

    // Run after every single test case completes
    @AfterEach
    void cleanUpTestData() {
        sharedMatches = null;
        sharedDeliveries = null;
        System.out.println("Test case finished. Cleaned temporary list data");

    }

//        Run once after all test methods are finished execution

    static void closeTestSuit(){
        iplService=null;
        System.out.println("All IPL Service Tests Successfully Executed ---");
    }

}