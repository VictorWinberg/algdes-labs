import java.io.IOException;
import java.util.*;

public class Matcher {

  public Matcher() throws IOException {
    Parser parser = new Parser();
    LinkedList<Man> singleMen = parser.getMen();
    LinkedList<Man> men = parser.getMen();
    Map<Integer, Woman> women = parser.getWomen();
    while(!singleMen.isEmpty()) {
      Man man = singleMen.pop();
      Woman woman = women.get(man.getPrefId());
      if(man.propose(woman)) {
        if(woman.getDumpedHusband() != null)
          singleMen.addFirst(woman.getDumpedHusband());
      } else {
        singleMen.addFirst(man);
      }
    }
    System.out.println("--- Matcher -------");
    for(Man man : men)
      System.out.println(man + " -- " + man.getWife());
  }

  public static void main(String[] args) throws IOException {
    new Matcher();
  }
}
