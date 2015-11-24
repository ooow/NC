package people;

import internalMail.Message;
import internalMail.PostOffice;

import java.util.ArrayList;

/**
 * Created by goga on 17.11.15.
 */

/////////////////////////// Шпион
public class Spy extends Postman {
    private final String post = "Postman";
    private ArrayList<Worker> contacts = new ArrayList<>();
    private ArrayList<Message> mails;
    private ArrayList<PostOffice> clients;
    private boolean Configs;

    public Spy(String name) {
        super(name);
    }

    public void setContact(Worker... contacts) {
        for (int i = 0; i < contacts.length; i++) {
            this.contacts.add(contacts[i]);
        }
    }

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
                for (int j = 0; j < mails.size(); j++) {
                    if (mails.get(j).type == 1) {
                        for (int k = 0; k < contacts.size(); k++) {
                            castC(contacts.get(k)).send(mails.get(j));
                            contacts.get(k).to_do_work();
                        }
                    }
                }
            }
    }
}

