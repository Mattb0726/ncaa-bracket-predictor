import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Handler {
	
	
	//Takes the string and the two sets, adds the game, determines if there is a new team, and allows to analyze team stats
	public static void readGameData(String g, TeamSet teamSet) {
		String[] data = g.split(",");
		
		//Ignore the opening round games, stored in data[1]
		if(!data[1].equalsIgnoreCase("Opening Round")) {
			//Create the new game object
			Game game = new Game(determineYear(data[0]), data[1], data[2], Integer.parseInt(data[3]), data[4],
					Integer.parseInt(data[5]), Integer.parseInt(data[6]), data[7], Integer.parseInt(data[8]), Integer.parseInt(data[9]));
			
			//Check to see if the team name for the winner and loser equals the team names of the current game
			//If both the winner and loser are in the current NCAA tournament, add this game to an array to be used later
			boolean containsWinner = false;
			boolean containsLoser = false;
			Team t;
			for(int i = 0; i < teamSet.getSize(); i++) {
				t = teamSet.get(i);
				if(t.getName().equalsIgnoreCase(game.getWinner())) {
					t.addHistoricalWin(game.getWinningScore(), game.getRound(), game.getYear());
					containsWinner = true;
				}
				if(t.getName().equalsIgnoreCase(game.getLoser())) {
					t.addHistoricalLoss(game.getLosingScore(), game.getRound(), game.getYear());
					containsLoser = false;
				}
				if(containsWinner && containsLoser) {
					//If this specific game has teams that both made the tournament, add it to the list
					TeamSet.addGameToList(game);
					break;
				}
			}
		}
	}
	
	public static int determineYear(String d) {
		String[] date = d.split("/");
		//Should be element 2, month, date, year
		return Integer.parseInt(date[2]);
	}

	//A helper method that returns a team from the string that is passed in
	public static Team readCurrentTeamData(String t) {
		//FORMAT: Name, Region, Seed
		String[] teamData = t.split(",");
		return new Team(teamData[0], teamData[1], Integer.parseInt(teamData[2]));
	}
	
	//Read in all of the data from the historical NCAA dataSet
	public static void readHistoricalData(String fileName, TeamSet teamSet) throws IOException{
		Scanner input = new Scanner(new File(fileName));
		String data = input.nextLine();
		while(!data.contains(",,,,")) {
			//Read the game data for each ncaa game, send in each line and the teamset
			readGameData(data, teamSet);
			data = input.nextLine();
		}
	}

	//Add all of the file names for each regular season data set into an array
	public static void addFileNames(ArrayList<String> fileName) throws IOException{
		Scanner regularSeasonData = new Scanner(new File("C:\\Users\\mattb\\Documents\\NCAAFileNames.txt"));
		String teamFile = regularSeasonData.nextLine();
		while(!teamFile.contains(",,,,")) {
			fileName.add(teamFile);
			teamFile = regularSeasonData.nextLine();
		}	
	}
	
	public static Team determineWinner(Team t1, Team t2) {
		int pointsT1 = 0;
		int pointsT2 = 0;
		
		//***Check regular season average ppg***
		if(t1.seasonStats.averagePPG > t2.seasonStats.averagePPG) {
			pointsT1 += 4;
		}else {
			pointsT2 += 4;
		}
		
		//***Check tournament average ppg***
		if(t1.tournamentStats.averagePPG > t2.tournamentStats.averagePPG) {
			pointsT1 += 2;
		}else {
			pointsT2 +=2;
		}
		
		//***Check season WL ratio***
		if(t1.seasonStats.wLRatio > t2.seasonStats.wLRatio) {
			pointsT1 += 2;
		}else {
			pointsT2 += 2;
		}
		
		//***Check for amount of tournament games played in history***
		int gameDiff = t1.tournamentStats.numGames - t2.tournamentStats.numGames;
		if(gameDiff >= 10) {
			pointsT1 += 4;
			if(t1.tournamentStats.wLRatio > t2.tournamentStats.wLRatio) {
				pointsT1 += 4;
			}else {
				pointsT2 += 2;
			}
		}else if(gameDiff <= -10) {
			pointsT2 += 4;
			if(t1.tournamentStats.wLRatio < t2.tournamentStats.wLRatio) {
				pointsT2 += 4;
			}else {
				pointsT1 += 2;
			}
		}else if(gameDiff < 10 && gameDiff > -10 && gameDiff > 0) {
			pointsT1 += 2;
		}else if(gameDiff < 10 && gameDiff > -10 && gameDiff < 0) {
			pointsT2 += 2;
		}else {
			//Do nothing
		}
		
		//***Check for amount of appearances (years) teams have played in the tournament***
		if(t1.tournamentStats.numberOfAppearances > t2.tournamentStats.numberOfAppearances) {
			pointsT1 += 2;
		}else {
			pointsT2 += 2;
		}
		
		//***Check the amount of championship wins***
		if(t1.tournamentStats.timesWon > t2.tournamentStats.timesWon) {
			pointsT1 += 4;
		}else {
			pointsT2 += 4;
		}
		
		//***Check the seed differences***
		int seedDiff = t1.seed - t2.seed;
		if(seedDiff >= 4) {
			pointsT1 += 6;
		}else if(seedDiff <= -4) {
			pointsT2 += 6;
		}else if(seedDiff < 4 && seedDiff > -4 && seedDiff > 0) {
			pointsT1 += 2;
		}else if(seedDiff < 4 && seedDiff > -4 && seedDiff < 0) {
			pointsT2 += 2;
		}else {
			//Do nothing
		}
		
		//***Add a random factor into the winning***
		Random rand = new Random();
		int randNum = (int) (100 * rand.nextDouble());
		if(randNum % 2 == 0) {
			pointsT1 += 3;
		}else {
			pointsT2 += 3;
		}
		
		//***Check to see who has the most points. If there is a tie, flip a coin***
		if(pointsT1 > pointsT2) {
			System.out.println(t1.getName() + " beat " + t2.getName());
			return t1;
		}else if(pointsT2 > pointsT1) {
			System.out.println(t2.getName() + " beat " + t1.getName());
			return t2;
		}else {
			randNum = (int) (100 * rand.nextDouble());
			if(randNum % 2 == 0) {
				System.out.println(t1.getName() + " beat " + t2.getName());
				return t1;
			}else {
				System.out.println(t2.getName() + " beat " + t1.getName());
				return t2;
			}
		}
	}

}

