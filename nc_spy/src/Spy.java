import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by goga on 11.11.15.
 */

// Шпион
class Spy extends Client{
    public Spy(String name, Client... clients)
    {
        super(name, clients);
    }
    @Override
    public void adopt_and_send(Message m) throws SQLException { // Секретные сообщения посылаются в Секретную базу
        if (m.getType() == 0) {
            Connection c = null;
            Statement stmt = null;
            c = DriverManager.getConnection("jdbc:sqlite:openstream.db");
            stmt = c.createStatement();
            String sql = "INSERT INTO OPENSTREAM VALUES ('"+ m.getSense() +"');";
            stmt.executeUpdate(sql);
        }
        if (m.getType() == 1)
        {
            String s = m.getSense();
            s += "      - by " + this.name;
            Connection c = null;
            Statement stmt = null;
            c = DriverManager.getConnection("jdbc:sqlite:secret.db");
            stmt = c.createStatement();
            String sql = "INSERT INTO SECRET VALUES ('"+ s +"');";
            stmt.executeUpdate(sql);
        }
        if (this.base.size()> 0)
            for (int i = 0; i < this.base.size(); i++) {
                base.get(i).adopt_and_send(m);
            }
    }

}

