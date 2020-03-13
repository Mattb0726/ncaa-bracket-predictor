import java.util.ArrayList;

public class Bracket {
	Node root;
	
	public Bracket(ArrayList<Team> t, int level){
		root = new Node();
		createFullTree(t, level);
	}
	
	//Creates each level of the binary tree, starting at the bottom of the tree where the 64 teams are
	public void createFullTree(ArrayList<Team> t, int level){
		ArrayList<Node> current = new ArrayList<Node>();
		ArrayList<Node> parentNodes = new ArrayList<Node>();
		//Loop through all of the levels
		for(int i = 0; i < level; i++) {
			if(i == 0) {
				//Create all of the team nodes
				for(Team x : t){
					current.add(new Node(x));
				}
			//If we are about to break out of the loop, set the left and right child of the root
			}else if(i == level - 1){
				root.lChild = current.get(0);
				root.rChild = current.get(1);
				root.team = Handler.determineWinner(root.lChild.team, root.rChild.team);
			//For every other level other than the root and bottom
			}else {
				for(int k = 0; k < current.size(); k+=2) {
					Node parent = new Node();
					parent.lChild = current.get(k);
					parent.rChild = current.get(k + 1);
					//Figures out the team for the next node and prints out the winner
					parent.team = Handler.determineWinner(parent.lChild.team, parent.rChild.team);
					parentNodes.add(parent);
				}
				current.clear();
				for(Node x : parentNodes) {
					current.add(x);
				}
				parentNodes.clear();
			}
		}
	}
}
