/**
 * Created by goga on 12.11.15.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Node{
    private ArrayList<Client> first_recipient = new ArrayList<>();
    private ArrayList<Message> message = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        Connection c = null;
        Statement stmt = null;
        Class.forName("org.sqlite.JDBC");

        c = DriverManager.getConnection("jdbc:sqlite:secret.db");
        String sql = "CREATE TABLE IF NOT EXISTS SECRET ('Messages' TEXT);";
        stmt = c.createStatement();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM SECRET";
        stmt = c.createStatement();
        stmt.executeUpdate(sql);

        c = DriverManager.getConnection("jdbc:sqlite:openstream.db");
        sql = "CREATE TABLE IF NOT EXISTS OPENSTREAM ('Messages' TEXT);";
        stmt = c.createStatement();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM OPENSTREAM";
        stmt = c.createStatement();
        stmt.executeUpdate(sql);

        Node sol = new Node();
        sol.initial_setup();
        sol.net_launch();
        stmt.close();
        c.close();
    }

    private void initial_setup() throws Exception {
        // Создаем клиентов сети (обычных пользователей и шпионов)
        Client Grace = new Client("Grace");
        Client Henry = new Client("Henry");
        Spy Oliver = new Spy("Oliver", Henry, Grace);
        Spy Ella = new Spy("Ella");
        Client Luke = new Client("luke", Ella);
        Client Jack = new Client("Jack", Luke, Oliver);
        first_recipient.add(Jack); // добавляем в список пользователей (первых получателей писем)

        //Добавляем сообщения для отправки в сеть ( 0 - обычное сообщение, 1 - секретное)
        message.add(new Message("Hello friends !", 0));
        message.add(new Message("how are you ?", 0));
        message.add(new Message("COME HOME", 1));
        message.add(new Message("TAKE ICE CREAM", 1));
        message.add(new Message("I saw a great movie", 0));
        message.add(new Message("GO HERE AND WATCH A MOVIE", 1));
        message.add(new Message("FASTLY!", 1));
        message.add(new Message("see it on the link : www.movie.com/123", 0));

    }

    private void net_launch() throws Exception {
        // Запускаем сеть, отправив все сообщения первым получателям
        for (int i = 0; i < message.size(); i++) {
            for (int j = 0; j < first_recipient.size(); j++) {
                first_recipient.get(j).adopt_and_send(message.get(i));
            }
        }
    }
}

