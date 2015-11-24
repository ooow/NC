package People;

import Internal_Mail.Message;
import Internal_Mail.Post_office;
import Secret_Room.Contact;

import java.util.ArrayList;

/**
 * Created by goga on 17.11.15.
 */

/////////////////////////// Шпион
public class Spy extends Postman{
    private final String post = "Postman";
    private ArrayList<Worker> contacts = new ArrayList<>();
    private ArrayList<Message> mails;
    private ArrayList<Post_office> clients;
    private boolean Configs;

    public Spy(String name) {
        super(name);
    }

    public void setContact(Worker...contacts)
    {
        for (int i = 0; i < contacts.length; i++) {
            this.contacts.add(contacts[i]);
        }
    }

    public void setConfigs(ArrayList<Message> mes, ArrayList<Post_office> reciver)
    {
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
                if (mails.get(j).type == 1)
                {
                    for (int k = 0; k < contacts.size(); k++) {
                        castC(contacts.get(k)).send(mails.get(j));
                        contacts.get(k).to_do_work();
                    }
                }
            }
        }
    }
}

