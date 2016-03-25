import java.util.*;

public class Man extends Person {

  private Woman wife;

  public Man(int id, String name) {
    super(id, name);
  }

  public int getPrefId() {
    return prefs.pop();
  }

  public boolean propose(Woman woman) {
    if(!woman.isEngaged()) {
      wife = woman;
      woman.setEngaged(true);
      return true;
    } else {
      return false;
    }
  }
}
