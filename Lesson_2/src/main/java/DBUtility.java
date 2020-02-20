import java.sql.*;
import java.util.ArrayList;

import static java.awt.SystemColor.TEXT;
import static java.sql.JDBCType.INTEGER;
import static javax.lang.model.type.TypeKind.UNION;
import static javax.swing.text.html.HTML.Tag.U;

public class DBUtility {
    /*
     * Каждый из тасков решается одним SQL запросом
     */

    /*
    Создать таблицу принтеры, Printer(id INTEGER AI PK U, model INTEGER, color TEXT, type TEXT, price INTEGER)
    добавить в нее 3 записи:
    1 1012 col laser 20000 (производитель HP)
    2 1010 bw jet 5000 (производитель Canon)
    3 1010 bw jet 5000 (производитель Canon)
    Каждая вставка в таблицу принтер должна отражаться добавлением записи в таблицу продукт
     */


    void AddPrinters(Statement stmt) {
        // TODO: 16.12.2019
        try {
            stmt.executeUpdate("INSERT INTO Printer(model, color, type, price) VALUES ('1012', 'col', 'laser', '20000');" +
                    "INSERT INTO Product(maker, model, type) VALUES ('HP', '1012', 'Printer');" +
                    "INSERT INTO Printer(model, color, type, price) VALUES ('1010', 'bw', 'jet', '5000');" +
                    "INSERT INTO Product(maker, model, type) VALUES ('Canon', '1010', 'Printer');" +
                    "INSERT INTO Printer(model, color, type, price) VALUES ('1010', 'bw', 'jet', '5000');" +
                    "INSERT INTO Product(maker, model, type) VALUES ('Canon', '1010', 'Printer');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Записи в таблицу Printer добавлены!!!");
    }


    public void createPrinterTable(Connection con, Statement  stmt) {
        // TODO: 16.12.2019
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS " + "Printer( id INTEGER PRIMARY KEY AUTOINCREMENT , model INTEGER, color TEXT, type TEXT, price INTEGER);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Таблица Printer создана!!!");
    }

    /*
     * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
     */

    public ArrayList<String> selectExpensivePC(Statement stmt) {
        //todo
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT DISTINCT model, speed, ram, hd, price FROM PC WHERE price > '15000';");
            while (rs.next()) {
                System.out.println(rs.getInt("model")  + " "
                        + rs.getInt("speed") + " " + rs.getInt("ram") + " "  + rs.getInt("hd") + " " + rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Метод должен вернуть список id ноутов, скорость процессора
     * которых выше чем 2500
     */

    public ArrayList<Integer> selectQuickLaptop(Statement stmt)  {
        // TODO: 16.12.2019
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT id FROM Laptop WHERE speed > '2500';");
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Метод должен вернуть список производителей которые
     *  делают и пк и ноутбуки
     */
    public ArrayList<String> selectMaker(Statement stmt) {
        ArrayList<String> ans = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT maker, count(maker) as counter FROM (SELECT DISTINCT * FROM Product) group by maker having  counter >= 2;");
            while (rs.next()) {
                ans.add(rs.getString("maker"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    /*
     * Метод должен вернуть максимальную среди всех произодителей
     * суммарную стоимость всех изделий типов ноутбук или компьютер,
     * произведенных одним производителем
     * Необходимо объединить таблицы продуктов ноутбуков и компьютеров
     * и отгрупировать по сумме прайсов после чего выбрать максимум
     * или сделать любым другим способом
     */

    public int makerWithMaxProceeds(Statement stmt){
        int result = 0;
        //todo
        try {
            ResultSet rs = stmt.executeQuery("SELECT MAX(SUMM) as maxsumm from(select distinct SUM(price) as SUMM from (select distinct  id, maker, price from PC join Product on pc.model = product.model " +
                    "UNION select distinct id, maker, price from Laptop join Product on Laptop.model = Product.model) group by maker);");
            rs.next();
            result = rs.getInt("maxsumm");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}