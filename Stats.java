public class Stats {
	
	int wins;
	int losses;
	double wLRatio;
	int numGames;
	double averagePPG;
	
	public Stats() {
		this.wins = 0;
		this.losses = 0;
		this.numGames = 0;
		this.wLRatio = 0.0;
		this.averagePPG = 0;
	}
	
	public Stats(int wins, int losses, int points) {
		this.wins = wins;
		this.losses = losses;
		this.numGames = 1;
		this.wLRatio = (double) wins / numGames;
		this.averagePPG = points;
	}
	
	public void updatePPG(int points) {
		averagePPG *= numGames;
		averagePPG += points;
		numGames++;
		averagePPG /= (double)numGames;
	}
	
	public void addWin(int points) {
		updatePPG(points); //Also increases the number of games
		wins++;
		wLRatio = (double) wins / numGames;
	}
	
	public void addLoss(int points) {
		updatePPG(points); //Also increases the number of games
		losses++;
		wLRatio = (double) wins / numGames;
	}
}
