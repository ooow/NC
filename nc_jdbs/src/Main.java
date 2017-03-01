import java.sql.*;

/**
 * Created by Гога on 24.02.2016.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql:BillSystem", "postgres", "zxcxz");
        PreparedStatement st = con.prepareStatement("INSERT into ORG (OID,TITLE) values (?,?)");
        for (int i = 3; i < 100; i++) {
            st.setInt(1, i);
            st.setString(2, "HTC");
            st.addBatch();
        }
        st.executeBatch();
        Statement st1 = con.createStatement();
        ResultSet rs = st1.executeQuery("select * from org");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }
    }
}
