package entities;

public class Hound extends Enemy {
    private Hunter hunter;
	
	public Hound(Coordinate position,Hunter hunter) {
		super(position);
		this.hunter = hunter;
		
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co, Graph g) {
		Coordinate target = null;
		if (hunter != null) {
			target = g.hound(co,hunter.getPosition(),this.position);
		} else {
			target = co;
		}
	
		return target;
	}
	
	
	
	public void setHunter (Hunter hunter) {
		this.hunter = hunter;
	}
	
	public String getName() {
		return "Hound";
	}
}
