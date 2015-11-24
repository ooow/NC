package Secret_Room;

import Internal_Mail.Message;

import java.util.ArrayList;

/**
 * Created by goga on 17.11.15.
 */

/////////////////////////// Секретная база(Нычка)
public class Secret_Base{
    private ArrayList<Message> secret_messages = new ArrayList<>();

    public void set_secret(Message message){
        secret_messages.add(message);
    }
    public void show_secret_info()
    {
        System.out.println("SECRET BASE STORE: ");
        for (int i = 0; i < secret_messages.size(); i++) {
            System.out.println(i + ". " + secret_messages.get(i).getSense());
        }
    }
}

