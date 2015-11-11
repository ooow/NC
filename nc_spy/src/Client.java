import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by goga on 11.11.15.
 */

public class Client{
    protected String name;
    protected int count_friends;
    protected ArrayList<Client> base = new ArrayList<>();

    public Client(String name, int n_friends)
    {
        this.name = name;
        this.count_friends = n_friends;
    }

    public void make_friends(ArrayList<Client> friends)
    {
        this.base = friends;
    }

    public String getName() {return name;}
    public Integer getNfr() {return count_friends;}

    public void adopt_and_send(String s) throws SQLException {
        if (s.startsWith("#0")) {
            s = s.substring(2);
            Connection c = null;
            Statement stmt = null;
            c = DriverManager.getConnection("jdbc:sqlite:openstream.db");
            stmt = c.createStatement();
            String sql = "INSERT INTO OPENSTREAM VALUES ('"+ s +"');";
            stmt.executeUpdate(sql);
        }
        if (count_friends > 0)
            for (int i = 0; i < count_friends; i++) {
                base.get(i).adopt_and_send(s);
            }
    }
}

