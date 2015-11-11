import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by goga on 11.11.15.
 */
class Spy extends Client{
    public Spy(String name, int n_friends) {
        super(name, n_friends);
    }

    @Override
    public void adopt_and_send(String s) throws SQLException {
        if (s.startsWith("#0")) {
            s = s.substring(2);
            s += "      - by " + this.name;
            Connection c = null;
            Statement stmt = null;
            c = DriverManager.getConnection("jdbc:sqlite:openstream.db");
            stmt = c.createStatement();
            String sql = "INSERT INTO OPENSTREAM VALUES ('"+ s +"');";
            stmt.executeUpdate(sql);
        }
        if (s.startsWith("#1"))
        {
            s = s.substring(2);
            s += "      - by " + this.name;
            Connection c = null;
            Statement stmt = null;
            c = DriverManager.getConnection("jdbc:sqlite:secret.db");
            stmt = c.createStatement();
            String sql = "INSERT INTO SECRET VALUES ('"+ s +"');";
            stmt.executeUpdate(sql);
        }
        if (count_friends > 0)
            for (int i = 0; i < count_friends; i++) {
                base.get(i).adopt_and_send(s);
            }
    }

}

