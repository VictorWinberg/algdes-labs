
public class Point {
	
	private String name;
	private double x, y;
	
	public Point(String name, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public String getName() {	return name;	}
	public double getX() 	{	return x;		}
	public double getY() 	{	return y;		}
	
	@Deprecated
	public double distanceTo(Point other) {
		return Math.hypot(x - other.x, y - other.y);
	}
	
	public double distanceToSqrt(Point other) {
		double dx = x - other.x;
		double dy = y - other.y;
		return dx * dx + dy * dy;
	}
	
	@Override
	public String toString() {
		return name + " (" + x + ", " + y + ")";
	}
}
