
public class Game {
	
	int year;
	String round;
	String region;
	int winningSeed;
	String winner;
	int winningScore;
	int losingSeed;
	String loser;
	int losingScore;
	int overtime;
	
	//This constructor is used for regular season data
	public Game(String winner, int winningScore, String loser, int losingScore) {
		year = 0;
		round = "";
		region = "";
		this.winningSeed = 0;
		this.winner = winner;
		this.winningScore = winningScore;
		this.losingSeed = 0;
		this.loser = loser;
		this.losingScore = losingScore;
		overtime = 0;
	}
	
	//This constructor would be used for HistoricalNCAA data
	public Game(int year, String round, String region, int winningSeed, String winner, int winningScore, int losingSeed, String loser, int losingScore, int overtime) {
		this.year = year;
		this.round = round;
		this.region = region;
		this.winningSeed = winningSeed;
		this.winner = winner;
		this.winningScore = winningScore;
		this.losingSeed = losingSeed;
		this.loser = loser;
		this.losingScore = losingScore;
		this.overtime = overtime;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getRound() {
		return round;
	}
	
	public String getRegion() {
		return region;
	}
	
	public int getWinningSeed() {
		return winningSeed;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public int getWinningScore() {
		return winningScore;
	}
	
	public int getLosingSeed() {
		return losingSeed;
	}
	
	public String getLoser() {
		return loser;
	}
	
	public int getLosingScore() {
		return losingScore;
	}
	
	public int getOvertime() {
		return overtime;
	}
	
	public String toString() {
		return String.format("%s, %s, %d, %s, %d, %d, %s, %d, %d", round, region, winningSeed, winner, winningScore, losingSeed, loser, losingScore, overtime);
	}
}
