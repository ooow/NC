import Client.*;
import Server.Chat;

import java.net.SocketException;

/**
 * Created by goga on 16.12.15.
 */
public class Main {
    public static void main(String[] args) throws SocketException {
        Client Mike = new Client("Mike", "localhost", 7771);    // Создаю 2 - х пользователей
        Client Rob = new Client("Rob", "localhost", 7772);
        Chat GreenChat = new Chat("localhost", 7777);           // Создаю Чат
        GreenChat.setUsers(Mike, Rob);                          // Добавляю пользователей в чат
        new Thread(GreenChat).start();
        new Thread(new ClientGUI(Mike, 150, 150)).start();
        new Thread(new ClientGUI(Rob, 850, 150)).start();
    }
}
