import java.util.*;

public class Man extends Person {

  private Woman wife;
  private LinkedList<Integer> prefs;

  public Man(int id, String name) {
    super(id, name);
    prefs = new LinkedList<Integer>();
  }

  public boolean propose(Woman woman) {
    if(!woman.isEngaged() || woman.prefer(this)) {
      wife = woman;
      wife.setHusband(this);
      return true;
    } else {
      return false;
    }
  }

  public void addPref(int id) { prefs.add(id); }
  public int getPrefId() {  return prefs.pop(); }
  public String getPrefs()    { return prefs.toString();  }
  public Woman getWife() {  return wife;        }
}
