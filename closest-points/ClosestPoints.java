import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Closest points
 * 
 * Algorithm https://rosettacode.org/wiki/Closest-pair_problem
 */
public class ClosestPoints {
	
	private List<Point> allPoints, allPointsByX, allPointsByY;
	
	public ClosestPoints(String path) throws IOException {
		String[] input = readFromFile(path).split("\n");
		allPoints = new LinkedList<Point>();
		buildStructure(input);
		allPointsByX = (List) ((LinkedList) allPoints).clone();
		allPointsByY = (List) ((LinkedList) allPoints).clone();
		allPointsByX.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return (int) (o1.getX() - o2.getX());
			}
		});
		allPointsByY.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return (int) (o1.getY() - o2.getY());
			}
		});
		System.out.print(allPoints.size() + " ");
		System.out.println(findClosestPair(allPointsByX, allPointsByY));
		
	}
	
	/**
	 * Finds and returns the closest pair of points by divide and conquer, O(n log n).
	 * @param pointsByX points sorted by x-coordinates
	 * @param pointsByY points sorted by y-coordinates
	 */
	private Pair findClosestPair(List<Point> pointsByX, List<Point> pointsByY) {
		Pair closest = new Pair(null, null);
		if (pointsByX.size() <= 3) {
			return bruteForceClosestPair(pointsByX);
		}
		
		List<Point> xL = pointsByX.subList(0, pointsByX.size()/2);
		List<Point> xR = pointsByX.subList(pointsByX.size()/2, pointsByX.size());
		List<Point> yL = new ArrayList<Point>();
		List<Point> yR = new ArrayList<Point>();
		
		for (Point p : xL) {	yL.add(p);	}
		for (Point p : xR) {	yR.add(p);	}
		
		Pair dL = findClosestPair(xL, yL);
		Pair dR = findClosestPair(xR, yR);
		closest = (dL.distSqrt() < dR.distSqrt()) ? dL : dR;

		// Middle points
		List<Point> yS = new ArrayList<Point>();
		// The most rightmost point of the left side ≈ middle point
		double x = xL.get(xL.size() - 1).getX();
		for (Point p : pointsByX) {
			if (Math.abs(p.getX() - x) < closest.distSqrt())
				yS.add(p);
		}
		int i = 0;
		for(Point p : yS) {
			for (int k = 1; k < 16; k++) {
				if ((k+i) >= yS.size()) 
					break;
				Pair pairIK = new Pair(p, yS.get(k+i));
				closest = (pairIK.distSqrt() < closest.distSqrt()) ? pairIK : closest;
			}
			i++;
		}
		return closest;
	}
	
	/**
	 *  Find and returns closest pair by brute-force, O(n^2)
	 * @param pointsByX points sorted by x-coordinates
	 */
	private Pair bruteForceClosestPair(List<Point> pointsByX) {
		Pair closest = new Pair(null, null);
		if(pointsByX.size() < 2)
			return closest;
		LinkedList<Point> temp = new LinkedList<Point>(pointsByX);
		for (Point px : pointsByX) {
			temp.poll();
			for (Point pt : temp) {
				Pair p = new Pair(px, pt);
				closest = (p.distSqrt() < closest.distSqrt()) ? p : closest;
			}
		}
		return closest;
	}

	private String readFromFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, java.nio.charset.Charset.defaultCharset()).trim();
	}
	
	private void buildStructure(String[] input) {
		for(int i = 0; i < input.length; i++) {
			String[] column = input[i].trim().split("\\s+");
			try {
				String name = column[0];
				double x = Double.parseDouble(column[1]);
				double y = Double.parseDouble(column[2]);				
				allPoints.add(new Point(name, x, y));
			} catch (Exception e) {
				// Ok my bad nvm then
			}
		}
	}

	public static void main(String[] args) throws IOException {
		if(args.length == 0)
			new ClosestPoints("closest-points/data/d1291-tsp.txt");
		else
			new ClosestPoints(args[0]);
	}
}
