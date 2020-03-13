public class Node {
	
	//The value that each node is holding
	Team team;
	
	Node lChild;
	Node rChild;
	
	public Node(Team t) {
		team = t;
		lChild = rChild = null;
	}
	
	public Node() {
		team = null;
		lChild = rChild = null;
	}
	
	public void setTeam(Team t) {
		team = t;
	}
	
	public String toString() {
		return team.getName();
	}
}
