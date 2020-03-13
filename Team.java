import java.util.ArrayList;

public class Team {
	String name;
	String region;
	int seed;
	
	Stats seasonStats;
	TournamentStats tournamentStats;
	
	ArrayList<Game> regularSeason;
	
	public Team(String name, String region, int seed) {
		this.name = name;
		this.region = region;
		this.seed = seed;
		
		this.seasonStats = new Stats();
		this.tournamentStats = new TournamentStats();
	}
	
	//Analyze a new game
	public void analyzeNewGame(boolean isWinner, boolean isNCAA, int year, String round, int seed, int score) {
		if(isWinner && isNCAA) {
			//Won an NCAA game
			tournamentStats.addWin(score, round, year);
		}else if(isWinner && !isNCAA){
			//Won a regular season game
			seasonStats.addWin(score);
			//**KEEP THE GAME DATA FOR LATER WHEN COMPARING NCAA GAMES. CHECK BOOK
		}else if(!isWinner && isNCAA) {
			//Lost an NCAA game
			tournamentStats.addLoss(score, round, year);
		}else {
			//Lost a regular season game
			seasonStats.addLoss(score);
			//**KEEP THE GAME DATA FOR LATER WHEN COMPARING NCAA GAMES. CHECK BOOK
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name + " (" + region + ") " + seed + ":\n" + /*seasonStats.toString() + */ tournamentStats.toString();
	}
	
	//Applies the stats of a win in the NCAA tournament
	public void addHistoricalWin(int points, String round, int year) {
		tournamentStats.addWin(points, round, year);
	}
	
	//Applies the stats of a loss in the NCAA tournament
	public void addHistoricalLoss(int points, String round, int year) {
		tournamentStats.addLoss(points, round, year);
	}
}
