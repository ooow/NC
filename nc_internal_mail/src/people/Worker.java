package people;

import secretRoom.Contact;

/**
 * Created by goga on 17.11.15.
 */
/////////////////////////// Работник
public interface Worker {
    public <T> void to_do_work();

    public String getPost();

    default <T extends Postman> T cast(Worker w) {
        return (T) w;
    }

    default <E extends Contact> E castC(Worker w) {
        return (E) w;
    }
}

