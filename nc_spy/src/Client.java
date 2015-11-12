import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by goga on 11.11.15.
 */

// Обычный пользователь сети
public class Client{
    protected String name;
    protected ArrayList<Client> base = new ArrayList<>();

    public Client(String name, Client... clients)
    {
        this.name = name;
        for (int i = 0; i < clients.length; i++) {
            base.add(clients[i]);
        }
    }

    public void adopt_and_send(Message m) throws SQLException { // Может хранить только обычные сообщения
        if (m.getType() == 0) {
            Connection c = null;
            Statement stmt = null;
            c = DriverManager.getConnection("jdbc:sqlite:openstream.db");
            stmt = c.createStatement();
            String sql = "INSERT INTO OPENSTREAM VALUES ('"+ m.getSense() +"');";
            stmt.executeUpdate(sql);
        }
        if (this.base.size() > 0)
            for (int i = 0; i < this.base.size(); i++) {
                base.get(i).adopt_and_send(m);
            }
    }
}

