package people;

/**
 * Created by goga on 17.11.15.
 */

/////////////////////////// Уборщик
public class Cleaner implements Worker {
    private final String myname;
    private final String post = "Cleaner";

    public Cleaner(String name) {
        this.myname = name;
    }

    ;

    @Override
    public void to_do_work() {
    }

    @Override
    public String getPost() {
        return this.post;
    }
}

