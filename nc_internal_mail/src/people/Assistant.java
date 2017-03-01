package people;

/**
 * Created by goga on 17.11.15.
 */

///////////////////////////  Помощник
public class Assistant implements Worker {
    private final String myname;
    private final String post = "Assistant";

    public Assistant(String name) {
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

