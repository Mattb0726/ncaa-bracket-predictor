import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamSet {
	
	ArrayList<Team> teamSet;

	static ArrayList<Game> bothTeamsInTournament = new ArrayList<Game>();
	
	public TeamSet(String fileName, ArrayList<String> fileNames) throws IOException {
		//Initialize the team set
		teamSet = new ArrayList<Team>();
		
		Scanner input;
		//Loop through all 64 team's regular season data found in the fileNames array
		for(String file : fileNames) {
			input = new Scanner(new File(file));
			String name = input.nextLine(); //Grab the team name from the top of the file
			Team temp = Handler.readCurrentTeamData(name); //Create a new team and add it to the teamSet
			teamSet.add(temp);
				
			//Analyze the data of each game and apply it to the team
			String game = input.nextLine();
			while(!game.contains(",,,,")) {
				//FORMAT: NameOpponent, teamScore, oppScore
				String[] data = game.split(",");
				int teamScore = Integer.parseInt(data[1]);
				int oppScore = Integer.parseInt(data[2]);
					
				//The team won the game ***
				if(teamScore > oppScore) {
					//Adds a win to the team and applies stats
					temp.analyzeNewGame(true, false, 0, "", 0, teamScore);
					if(findTeam(data[0]) != null) {
						addGameToList(new Game(name, teamScore, data[0], oppScore));
					}
				//The team lost the game ***
				}else {
					//Adds a loss to the team and applies stats
					temp.analyzeNewGame(false, false, 0, "", 0, teamScore);
					if(findTeam(data[0]) != null) {
							addGameToList(new Game(data[0], oppScore, name, teamScore));
					}
				}
					game = input.nextLine();
			}
		}	
	}
	
	public static void addGameToList(Game g) {
		bothTeamsInTournament.add(g);
	}
	
	public String toString() {
		String val = "";
		for(Team t : teamSet) {
			val += t.toString();
		}
		return val;
	}
	
	public void addTeam(Team t) {
		teamSet.add(t);
	}
	
	public int getSize() {
		return teamSet.size();
	}
	
	public Team get(int i) {
		return teamSet.get(i);
	}
	
	public Team findTeam(String name) {
		for(Team t : teamSet) {
			if(t.getName().equalsIgnoreCase(name)) {
				return t;
			}
		}
		return null;
	}
}
