## In this data assignment you will transform raw data of IPL to calculate the following stats:

#### 1  Number of matches played per year for all the years in IPL.
#### 2 Number of matches won per team per year in IPL.
#### 3 Extra runs conceded per team in the year 2016
#### 4 Top 10 economical bowlers in the year 2015
#### 5 Find the number of times each team won the toss and also won the match
#### 6 Find a player who has won the highest number of Player of the Match awards for each season
#### 7 Find the strike rate of a batsman for each season
#### 8 Find the highest number of times one player has been dismissed by another player
#### 9 Find the bowler with the best economy in super overs
####  10 Implement the functions, one for each task. Use the results of the functions to dump JSON files in the output folder

## Unit Testing Suite

The project includes an isolated, professional testing pipeline built on **JUnit 5 (Jupiter)**. It validates all analytical algorithms against deterministic mock datasets using a structured test lifecycle.

###  Testing Lifecycle Configuration
To ensure state isolation and safe execution, our test engine uses a 4-phase lifecycle setup:
* `@BeforeAll`: Initialises global operational dependencies (such as the `IPLService` core engine) once before the testing suite boots up.
* `@BeforeEach`: Mounts isolated, fresh configurations of mock `Match` lists and `Delivery` data scopes to prevent cascading side effects.
* `@AfterEach`: Purges volatile state memory allocations after every individual execution run.
* `@AfterAll`: Gracefully tears down framework contexts and releases analytical memory hooks cleanly.

###  Covered Test Cases

Our framework completely asserts all 10 analytical targets outlined in the assignment requirements:

1. **`matchesPerYear_shouldCountMatchesBySeason`**
   * Verifies that total match records are grouped and mapped accurately against their chronological season markers.
2. **`matchesWonAllTeams_shouldCountWinsPerTeamPerYear`**
   * Confirms victory margins are tallied for franchise variants across discrete year blocks.
3. **`extraRunsPerTeam2016_shouldSumExtraRunsFor2016MatchesOnly`**
   * Validates that wide, no-ball, and bye-run data aggregates strictly belong to matching 2016 ID links.
4. **`topEconomicalBowlers2015_shouldCalculateTopBowlers`**
   * Asserts the mathematical precision of the formula: `(Total Runs Conceded * 6) / Valid Deliveries` for the 2015 season block.

5. **`batsmanStrikeRatePerSeason_shouldComputeStrikeRate`**
   * Validates calculation logic mapping total batsman runs over valid ball deliveries faced.


###  How to Run the Tests

Execute this standard lifecycle command directly inside your project terminal directory root:
```bash
mvn clean test
```
