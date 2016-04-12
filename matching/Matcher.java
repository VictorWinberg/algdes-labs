import java.io.IOException;
import java.util.*;

public class Matcher {

	public Matcher() throws IOException {
		long startTime = System.currentTimeMillis();
		Parser parser = new Parser();
		LinkedList<Man> singleMen = parser.getMen();
		LinkedList<Man> men = parser.getMen();
		Map<Integer, Woman> women = parser.getWomen();
		while (!singleMen.isEmpty()) {
			Man man = singleMen.pop();
			Woman woman = women.get(man.getPrefId());
			if (man.propose(woman)) {
				if (woman.getDumpedHusband() != null)
					singleMen.addFirst(woman.getDumpedHusband());
			} else {
				singleMen.addFirst(man);
			}
		}
		long totalTime = System.currentTimeMillis() - startTime;
		for (Man man : men)
			System.out.println(man + " -- " + man.getWife());
		System.out.println("Time: " + totalTime);
	}

	public static void main(String[] args) throws IOException {
		new Matcher();
	}
}
