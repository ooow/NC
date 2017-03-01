package secretRoom;

import internalMail.Message;
import people.Worker;

/**
 * Created by goga on 17.11.15.
 */

/////////////////////////// Cвязной
public class Contact implements Worker {
    private String name;
    private Message mes;
    private SecretBase sb;
    private boolean Configs;

    public Contact(String name, SecretBase sb) {
        this.name = name;
        this.sb = sb;
    }

    public void send(Message mail) {
        this.Configs = true;
        this.mes = mail;
    }

    public void to_do_work() {
        if (Configs) {
            this.sb.set_secret(mes);
            Configs = false;
        }
    }

    @Override
    public String getPost() {
        return null;
    }
}

