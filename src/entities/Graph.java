package entities;
import java.util.*;

public class Graph {

	private ArrayList<Coordinate> coordList;
	private ArrayList<Edge> edgeList;
	private int nV;
	private int coordConst;
	private int height;
	private int width;
	
	
	
	public Graph(int height,int width) {
		this.coordList = new ArrayList<Coordinate>();
		this.edgeList = new ArrayList<Edge>();
		this.nV = 0;
		this.coordConst = 32;
		this.height = height;
		this.width = width;
	}
	public void addCoordinate(Coordinate c) {
		this.coordList.add(c);
		this.nV++;
	}
	public void addEdge(Edge e) {
		this.edgeList.add(e);
	}
	
	public void generateEdges() {
		for (Coordinate object1: this.coordList) {
			for (Coordinate object2: this.coordList) {
				if (object2.equals(object1))
					continue;
				if (isAdjacent(object1,object2)) {
					this.addEdge(new Edge(object1,object2));
				}
			}
		}
	}
	
	public boolean isAdjacent(Coordinate a, Coordinate b) {
		boolean check = false;
		int ax = a.getxPosition()/coordConst;
		int ay = a.getyPosition()/coordConst;
		int bx = b.getxPosition()/coordConst;
		int by = b.getyPosition()/coordConst;
		
		if ((ax == bx) && (ay-1 == by || ay+1 == by)) {
		    check = true;
		} else if((ax-1 == bx || ax + 1 == bx) && ay == by) {
		    check = true;
			
		}
		
		return check;
	}
	
	public Coordinate BFS(Coordinate src, Coordinate dest) {
		Coordinate cur = null;
		int[] visited = new int[this.nV];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int srcIndex = coordList.indexOf(src);
		int destIndex = coordList.indexOf(dest);
		int vertex;
		int w;
		boolean found = false;
		
		for (int i = 0; i <nV; i++) {
		    visited[i] = -1;	
		}
		visited[srcIndex] = -2;
		queue.add(srcIndex);
		
		while (queue.size() != 0) {
			vertex = queue.poll();
			//System.out.println("vertex is " + vertex);
			for (w = 0; w < nV; w++) {
			    if (w == vertex)
			    	continue;
			    
			    if (visited[w] == -1 && hasEdge(coordList.get(vertex),coordList.get(w))) {
			    	visited[w] = vertex;
			    	queue.add(w);
			    	if (w == destIndex) {
			    		found = true;
			  
			    		break;
			    	}
			    }
			}
			
		}
		if (found) {
			int temp = destIndex;
			for (int curValue = destIndex; curValue != srcIndex; curValue = visited[curValue]) {
				temp = curValue;
			}
			cur = coordList.get(temp);
		} else {
			cur = src;
		}
		
		return cur;
	}
	
	public boolean hasEdge(Coordinate a, Coordinate b) {
		boolean check = false;
	    for (Edge object: edgeList) {
	    	if (a == object.getSrc() && b == object.getDest()) {
	    		check = true;
	    		break;
	    	}
	    }
	    return check;
	}

 
    
    public Coordinate moveAway(Coordinate player, Coordinate enemy) {
           	
        Coordinate point = enemy;
        int ax = player.getxPosition()/coordConst;
        int ay = player.getyPosition()/coordConst;
        int bx = enemy.getxPosition()/coordConst;
        int by = enemy.getyPosition()/coordConst;
        
        int differenceX = ax - bx;
        int differenceY = ay -by;
        double totalDistance;
        
       
        double curDistance = Math.hypot(differenceX,differenceY);
        	
        	for (Coordinate object: coordList) {
        		if (isAdjacent(enemy,object)) {
        		    differenceX = object.getxPosition()/coordConst - ax;
        		    differenceY = object.getyPosition()/coordConst - ay;
        		    totalDistance = Math.hypot(differenceX, differenceY);
        	
        	        if (totalDistance > curDistance) {
        	    	    curDistance = totalDistance;
        	    	    point = object;
        	        }
        		}
        	}
        
     
        
        return point;
	
    }
   
    public boolean availablePoint(int x , int y) {
        boolean check = false;  	
    
    	for (Coordinate object: coordList) {
    	    if (x == object.getxPosition() && y == object.getyPosition()) {
    	    	check = true;
    	    	break;
    	    }
    	}
    		return check; 
    }
    
    public Coordinate getPoint(int x,int y) {
    	Coordinate target = null;
    	
    	for (Coordinate object: coordList) {
    	    if (x == object.getxPosition() && y == object.getyPosition()) {
    	    	target = object;
    	    	break;
    	    }
    	}
    		return target; 
    }
    
    
    public Coordinate between(Coordinate player, Coordinate enemy) {
    	Coordinate target = enemy;
    	int ax = player.getxPosition()/coordConst;
        int ay = player.getyPosition()/coordConst;
        int bx = enemy.getxPosition()/coordConst;
        int by = enemy.getyPosition()/coordConst;
        
        int midpointX = (ax + bx)/2;
        int midpointY = (ay + by)/2;
    	double curDistance = -1;
    	double totalDistance;
    	
    	for (Coordinate object: coordList) {
    	    totalDistance = Math.hypot(midpointX - object.getxPosition()/coordConst, midpointY - object.getyPosition()/coordConst);
    	    if (curDistance == -1 || curDistance > totalDistance) {
    	    	curDistance = totalDistance;
    	    	target = object;
    	    }
    	}
        
    	return target;
    }
    
    public Coordinate hound(Coordinate player , Coordinate enemy,Coordinate hound) {
    	Coordinate target = player;
    	int ax = player.getxPosition()/coordConst;
        int ay = player.getyPosition()/coordConst;
        int bx = enemy.getxPosition()/coordConst;
        int by = enemy.getyPosition()/coordConst;
     
        //player ahead in x coordinate
        int houndx;
        int houndy;
        if (ax -bx > 0) {
        	houndx = 1;
        } else if (ax - bx < 0) { //player behind in x coordinate
        	houndx = -1;
        } else { //player same x row as 
        	houndx = 0;
        }
        //player ahead in y coordinate
        if (ay -by > 0) {
        	houndy = 1;
        } else if (ay - by < 0) { //player behind in y coordinate
        	houndy = -1;
        } else { //player same y row as 
        	houndy = 0;
        }
        
        boolean check = false;
        while (!check) {
        	if (coordConst*(ax + houndx) == hound.getxPosition() && coordConst*(ay+houndy) == hound.getyPosition()) {
        		target = hound;
        		check = true;
      
        	} else {
        	    if (availablePoint(coordConst*(ax + houndx),coordConst*(ay+houndy) )) {
        		    target = getPoint(coordConst*(ax + houndx),coordConst*(ay+houndy));
        		
        		    check = true;
        	    } else {
        		    houndx+=houndx;
        		    houndy+=houndy;
        		
        	    }
        	    if (Math.abs(ax + houndx) > width || Math.abs(by+houndy) > height) {
        		    check = true;
        		
        		    target = player; //not sure about this
        	    }
        	}
        }
        
        
    	return target;
    	
    }
    
    public ArrayList<Coordinate> getCoords() {
    	return this.coordList;
    }
    
    public ArrayList<Edge> getEdges() {
    	return this.edgeList;
    }
   
    
}
