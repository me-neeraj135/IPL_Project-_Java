# IPL Data Analysis Project

This project provides specific analytical insights into historical Indian Premier League (IPL) matches and deliveries data. Using Java, it parses the dataset to compute tournament trends, team performances, and individual player statistics.

---

##  Key Metrics & Deliverables

The application is built to compute and display the following four data points:

### 1. Matches Played per Year
Calculates the total number of matches played across all seasons in the history of the IPL.
* **Output:** Year-by-year breakdown (e.g., `2008: 58`, `2009: 57`, etc.).

### 2. Overall Matches Won by Teams
Calculates the cumulative number of matches won by each team from the inception of the IPL up to the latest available data.
* **Output:** Team name and their total career wins.

### 3. 2016 Extra Runs Conceded
Analyzes the deliveries data from the 2016 season to identify the extra runs (wides, no-balls, leg byes, byes, penalties) conceded by each participating franchise.
* **Output:** Team name and the exact count of extra runs conceded.

### 4. 2015 Top Performers
Extracts individual player performances for the 2015 season:
* **Top Economical Bowlers:** Calculated as `(Total Runs Conceded / Balls Bowled) * 6` for bowlers with a minimum delivery threshold.
* **Top Batsmen:** Ranked by total runs scored off the bat.

---

##  Project Architecture

The application is structured into distinct layers to separate data ingestion from business logic:

```text
├── data/                  # Source CSV/JSON dataset files
├── src/
│   ├── model/             # POJOs (Match, Delivery, PlayerStats)
│   ├── parser/            # Generic data parser and CSV reader
│   └── service/           # Analytics engine and metric calculators
└── README.md
```

---

##  Dataset Specifications

The analysis relies on two primary dataset files placed in the `data/` directory:

1. **`matches.csv`**: Contains match-level information including `id`, `season`, `city`, `date`, `team1`, `team2`, `toss_winner`, `toss_decision`, `result`, `dl_applied`, `winner`, `win_by_runs`, `win_by_wickets`, `player_of_match`, `venue`.
2. **`deliveries.csv`**: Contains ball-by-ball details including `match_id`, `inning`, `batting_team`, `bowling_team`, `over`, `ball`, `batsman`, `non_striker`, `bowler`, `is_super_over`, `wide_runs`, `bye_runs`, `legbye_runs`, `noball_runs`, `penalty_runs`, `batsman_runs`, `extra_runs`, `total_runs`.

---
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

##  Technologies Used

* **Core Language:** Java 

