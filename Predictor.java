import java.io.IOException;
import java.util.ArrayList;

public class Predictor {
	
	public static void main(String[] args) throws IOException{
		
		//Fill this ArrayList with the file names of the regular season data
		ArrayList<String> fileNameRegularSeason = new ArrayList<String>();
		Handler.addFileNames(fileNameRegularSeason);
		
		//Read in the teams that will be participating in the bracket, analyze the regular season data
		TeamSet ncaaTeams = new TeamSet("C:\\Users\\mattb\\Documents\\NCAACurrentTeams.txt", fileNameRegularSeason);
		
		//Run this method to read in all of the historical data (2000+ games)
		Handler.readHistoricalData("C:\\Users\\mattb\\Documents\\NCAAData.csv", ncaaTeams);
		
		//Create the bracket(Has 7 levels to the bracket) and print the winners of each game in the bracket
		Bracket tournament = new Bracket(ncaaTeams.teamSet, 7);
	}
}
