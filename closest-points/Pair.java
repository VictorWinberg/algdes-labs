
public class Pair {

	private Point p1, p2;
	
	public Pair(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public double distSqrt() {
		if(p1 != null && p2 != null)
			return p1.distanceToSqrt(p2);
		else 
			return Double.MAX_VALUE;
	}
	
	@Override
	public String toString() {
		return Math.sqrt(distSqrt()) + " [" + p1 + " -- " + p2 + "]";
	}
}
