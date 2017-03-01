package people;

import internalMail.Message;
import internalMail.PostOffice;

import java.util.ArrayList;

/**
 * Created by goga on 17.11.15.
 */

/////////////////////////// Почтальон
public class Postman implements Worker {
    private final String myname;
    private final String post = "Postman";
    private ArrayList<Message> mails;
    private ArrayList<PostOffice> clients;
    private boolean Configs;

    public Postman(String name) {
        this.myname = name;
    }

    ;

    public void setConfigs(ArrayList<Message> mes, ArrayList<PostOffice> reciver) {
        this.mails = new ArrayList<>();
        this.mails = mes;
        this.clients = new ArrayList<>();
        this.clients = reciver;
        this.Configs = true;
    }

    @Override
    public void to_do_work() {
        if (Configs)
            for (int i = 0; i < clients.size(); i++) {
                clients.get(i).receive_mail(mails);
            }
    }

    @Override
    public String getPost() {
        return this.post;
    }

}

