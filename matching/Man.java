import java.util.*;

public class Man extends Person {

  private Woman wife;

  public Man(int id, String name) {
    super(id, name);
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

  public int getPrefId() {  return prefs.pop(); }
  public Woman getWife() {  return wife;        }
}
