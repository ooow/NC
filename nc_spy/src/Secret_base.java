import java.util.ArrayList;
/**
 * Created by goga on 11.11.15.
 */


public class Secret_base extends Spy {
    public Secret_base(String name, int n_friends) {
        super(name, n_friends);
    }

    protected void send_to_nichka(String s)
    {
        for (int i = 0; i < secret_store.size(); i++) {
            this.store.add(secret_store.get(i));
        }
    }

    public void show()
    {
        System.out.println("SECRET MESSAGES");
        for (int i = 0; i < store.size(); i++) {
            System.out.println(store.get(i));
        }
    }
}