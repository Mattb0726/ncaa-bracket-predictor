
public class TournamentStats {
	int wins;
	int losses;
	int numGames;
	double wLRatio;
	double averagePPG;
	
	int lastYearWon;
	int lastYearParticipating;
	int numberOfAppearances;

	//Could be improved by checking each rounds WL ratio
	int timesWon64;
	int timesWon32;
	int timesWon16;
	int timesWon8;
	int timesWonSemi;
	int timesWon;
	
	double wlRatio64;
	double wlRatio32;
	double wlRatio16;
	double wlRatio8;
	double wlRatioSemi;
	double wlRatioNationals;
	
	public TournamentStats() {
		this.wins = 0;
		this.losses = 0;
		this.numGames = 0;
		this.wLRatio = 0.0;
		this.averagePPG = 0;
		
		this.lastYearWon = 0;
		this.lastYearParticipating = 0;
		this.numberOfAppearances = 1;
		
		this.timesWon64 = wins;
		this.timesWon32 = 0;
		this.timesWon16 = 0;
		this.timesWon8 = 0;
		this.timesWonSemi = 0;
		this.timesWon = 0;
		
		this.wlRatio64 = (double) timesWon64 / numberOfAppearances;
		this.wlRatio32 = timesWon64 == 0 ? 0.0 : (double) timesWon32 / timesWon64;
		this.wlRatio16 = timesWon32 == 0 ? 0.0 : (double) timesWon16 / timesWon32;
		this.wlRatio8 = timesWon16 == 0 ? 0.0 : (double) timesWon8 / timesWon16;
		this.wlRatioSemi = timesWon8 == 0 ? 0.0 : (double) timesWonSemi / timesWon8;
		this.wlRatioNationals = timesWonSemi == 0 ? 0.0 : (double) timesWon / timesWonSemi;
	}
	
	public void updatePPG(int points) {
		averagePPG *= numGames;
		averagePPG += points;
		numGames++;
		averagePPG /= (double)numGames;
	}
	
	public void addWin(int points, String round, int year) {
		updatePPG(points); //Also increases the number of games
		wins++;
		wLRatio = losses == 0 ? 1.0 : (double) wins / numGames;
		if(lastYearParticipating != year) {
			lastYearParticipating = year;
			numberOfAppearances++;
		}
		//You only need to calculate the ratios for winning a championship game, no need for losing the game
		if(round.equalsIgnoreCase("National Championship")) {
			lastYearWon = year;
			timesWon++;
			wlRatioNationals = (double) timesWon / timesWonSemi;
		}else if(round.equalsIgnoreCase("Round of 64")) {
			timesWon64++;
			wlRatio64 = (double) timesWon64 / numberOfAppearances;
			
		}else if(round.equalsIgnoreCase("Round of 32")) {
			timesWon32++;
			wlRatio32 = (double) timesWon32 / timesWon64;
			
		}else if(round.equalsIgnoreCase("Sweet Sixteen")) {
			timesWon16++;
			wlRatio16 = (double) timesWon16 / timesWon32;
			
		}else if(round.equalsIgnoreCase("Elite Eight")) {
			timesWon8++;
			wlRatio8 = (double) timesWon8 / timesWon16;
			
		}else if(round.equalsIgnoreCase("National Semifinals")) {
			timesWonSemi++;
			wlRatioSemi = (double) timesWonSemi / timesWon8;
		}
		
	}
	
	public void addLoss(int points, String round, int year) {
		updatePPG(points); //Also increases the number of games
		losses++;
		wLRatio = (double) wins / numGames;
		if(lastYearParticipating != year) {
			lastYearParticipating = year;
			numberOfAppearances++;
		}
	}
	
	public String toString() {
		return "Wins: " + wins + "\nLosses: " + losses + "\nWLRatio: " + wLRatio + "\nPPG: " + averagePPG + "\n";
	}
}
