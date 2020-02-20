import java.sql.*;
import java.util.Arrays;


public class DBClass {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        String con = "jdbc:sqlite:homework.db";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = conn.createStatement();
        DBUtility dBUtility = new DBUtility();
        dBUtility.createPrinterTable(conn, stmt);
        dBUtility.AddPrinters(stmt);
        dBUtility.selectExpensivePC(stmt);
        dBUtility.selectQuickLaptop(stmt);
        System.out.println(dBUtility.selectMaker(stmt));
        System.out.println(dBUtility.makerWithMaxProceeds(stmt));
    }
}