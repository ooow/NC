import java.util.HashMap;
import java.util.Map;

/**
 * Created by goga on 30.11.15.
 */

class Hasher {
    private int i;

    Hasher(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Hasher && ((Hasher) o).i == this.i)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return this.i;
    }
}

public class Main {
    public static void main(String[] args) {
        Map<Hasher, String> m = new HashMap<>();
        m.put(new Hasher(7), "Hello");
        m.put(new Hasher(2), "World");
        m.put(new Hasher(9), "!!!");
        System.out.println(m.get(new Hasher(7)));
        System.out.println(m.get(new Hasher(2)));
        System.out.println(m.get(new Hasher(9)));
    }
}

