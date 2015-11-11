/**
 * Created by goga on 11.11.15.
 */

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Messager {
    private PrintWriter out;
    private int n;
    private int n_friends;
    private ArrayList<String>  ms = new ArrayList<>();
    private ArrayList<Client> people = new ArrayList<>();
    private ArrayList<Spy> spy = new ArrayList<>();
    private ArrayList<String> fr= new ArrayList<>();


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

        Messager sol = new Messager();
        sol.read();
        sol.make_messager();
        sol.go_messager();
        stmt.close();
        c.close();
    }

    private void read() throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        n = sc.nextInt();
        String connect = sc.next();
        if("Messages:".equals(connect)) {
            for (int i = 0; i < n; i++) {
                String s = sc.nextLine();
                if (!"".equals(s))
                    ms.add(i, s);
                else i--;
            }
        }
        n_friends = sc.nextInt();
        connect = sc.next();
        if("People:".equals(connect))
        for (int i = 0; i < n_friends; i++) {
            String s = sc.next();
            if (s.startsWith("#0")) {
                s = sc.next();
                int n_friends = sc.nextInt();
                Client a = new Client(s, n_friends);
                people.add(a);
                for (int j = 0; j < n_friends; j++) {
                    fr.add(sc.next());
                }

            }
            else {
                s = sc.next();
                int n_friends = sc.nextInt();
                Spy a = new Spy(s, n_friends);
                spy.add(a);
                for (int j = 0; j < n_friends; j++) {
                    fr.add(sc.next());
                }
            }
        }
    }

    private void make_messager() throws Exception {
        int next = 0;
        for (int i = 0; i < people.size(); i++) {
            Client a = people.get(i);
            ArrayList<Client> newFriends = new ArrayList<>();
            for (int j = 0; j < a.getNfr(); j++) {
                String drug = fr.get(next);
                next++;
                for (int k = 0; k < people.size(); k++) {
                    if (drug.equals(people.get(k).name))
                        newFriends.add(people.get(k));
                }
                for (int k = 0; k < spy.size(); k++) {
                    if (drug.equals(spy.get(k).name))
                        newFriends.add(spy.get(k));
                }
            }
            a.make_friends(newFriends);
        }
        for (int i = 0; i < spy.size(); i++) {
            Client a = spy.get(i);
            ArrayList<Client> newFriends = new ArrayList<>();
            for (int j = 0; j < a.getNfr(); j++) {
                String drug = fr.get(next);
                next++;
                for (int k = 0; k < people.size(); k++) {
                    if (drug.equals(people.get(k).name))
                        newFriends.add(people.get(k));
                }
                for (int k = 0; k < spy.size(); k++) {
                    if (drug.equals(spy.get(k).name))
                        newFriends.add(spy.get(k));
                }
            }
            a.make_friends(newFriends);
        }
    }

    private void go_messager() throws Exception {
        for (int i = 0; i < n; i++) {
            people.get(0).adopt_and_send(ms.get(i));
        }
    }

}