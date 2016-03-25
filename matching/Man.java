import java.util.*;

public class Man extends Person implements Comparable<Man> {

  private Woman wife;

  public Man(int id, String name) {
    super(id, name);
  }

  public int getPrefId() {
    return prefs.pop();
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

  public Woman getWife() {
    return wife;
  }

  @Override
  public int compareTo(Man other) {
    return this.id - other.id;
  }
}
