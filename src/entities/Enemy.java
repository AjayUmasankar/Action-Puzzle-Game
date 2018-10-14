
package entities;

public abstract class Enemy extends Entity{

    //protected Coordinate position;
    
	public Enemy (Coordinate position){
		super(position);
	    this.movable = true;
	}
	
	public abstract Coordinate getTargetSpace(Coordinate co,Graph g);
	
	
	public Coordinate move(Coordinate co,Graph g) {
		Coordinate move = getTargetSpace(co,g);
		g.addCoordinate(position); //need this?
		g.generateEdges();
		if (move == co && g.isAdjacent(position, co)) {
			move = position;
		} else if (move != position){
		    move = g.BFS(this.position, move,co);
		}
		
		return move;
	}
	

	//@Override
	public Coordinate getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	//@Override
	public int getX() {
		// TODO Auto-generated method stub
		return position.getX();
	}

	//@Override
	public int returnY() {
		// TODO Auto-generated method stub
		return position.getY();
	}

	//@Override
	public boolean willCollide(Coordinate otherPos) {
		// TODO Auto-generated method stub
		return position.equals(otherPos);
	}
	
	@Override
	public boolean interactWithBomb() {
		return true;
	}
	
	
	public Coordinate interactWithPlayer(Player player) {
		if(player.hasItem("Sword")) {
			player.hitUsingSword();
			return position;
		} else if (player.hasItem("InvincibilityPotion")) {
			return position;
		} else {
			player.setState(0);
		}
		return null;

	}
	

}

	