package org.example.cart;

import org.example.cart.Util.*;

import java.util.*;
import java.sql.*;

public class Connector {
    static String url = "jdbc:mysql://localhost:3306/ABC";
    static String uname = "huetr";
    static String pass = "12345678";
    static Statement stmt = null;

    static Statement getStatement() {
        if(stmt != null) return stmt;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            try {
                stmt = con.createStatement();
                return stmt;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static List<ItemDTO> selectItem() {
        Statement stmt = getStatement();

        String selectSql = "SELECT * from store";
        List<ItemDTO> items= new ArrayList<>();
        try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
            while (resultSet.next()) {
                Item item = new Item(resultSet.getInt(1), resultSet.getString(2));
                ItemDTO dto = ItemMapper.getInstance().toDTO(item);
                items.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    static List<OrderDTO> selectOrder() {
        Statement stmt = getStatement();

        String selectSql = "SELECT * from cart join store on cart.itemID = store.itemID";
        List<OrderDTO> items= new ArrayList<>();
        try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
            while (resultSet.next()) {
                Order order = new Order(resultSet.getInt(1), resultSet.getInt(2));
                OrderDTO dto = OrderMapper.getInstance().toDTO(order, resultSet.getString(4));
                items.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    static List<OrderDTO> selectOrderConditional(String condition) {
        Statement stmt = getStatement();
        String selectSql = "SELECT * from cart where " + condition;
        List<OrderDTO> items= new ArrayList<>();
        
        try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
            while (resultSet.next()) {
                items.add(new OrderDTO(resultSet.getInt(1), null, resultSet.getInt(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    static List<ItemDTO> selectItemConditional(String condition) {
        Statement stmt = getStatement();
        String selectSql = "SELECT * from store where " + condition;
        List<ItemDTO> items= new ArrayList<>();

        try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
            while (resultSet.next()) {
                Item item = new Item(resultSet.getInt(1), resultSet.getString(2));
                ItemDTO dto = ItemMapper.getInstance().toDTO(item);
                items.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    static void insert(String table, Map<String, String> values) {
        String insertSql = "insert into " + table + " (";

        int i = 0;
        for(String x: values.keySet()) {
            i++;
            if(i < values.size()) insertSql += x + ", ";
            else insertSql += x + ") values(";
        }

        i = 0;
        for(String x: values.values()) {
            i++;
            if(i < values.size()) insertSql += x + ", ";
            else insertSql += x + ")";
        }

        try {
            System.out.println(insertSql);
            stmt.executeUpdate(insertSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void update(String table, Map<String, String> values) {
        String updateSql = "update " + table + " set ";
        int i = 0;
        for(Map.Entry<String, String> pair : values.entrySet()) {
            i++;
            if(i < values.size())  updateSql += pair.getKey() + "=" + pair.getValue() + ", ";
            else updateSql += pair.getKey() + "=" + pair.getValue();
        }
        updateSql += " where itemID=" + values.get("itemID");

        try {
            System.out.println(updateSql);
            stmt.executeUpdate(updateSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertOrder(Map<String, String> values) {
        Statement stmt = getStatement();
        String condition = "itemID=" + values.get("itemID");
        List<OrderDTO> items = selectOrderConditional(condition);

        if(items.size() > 0) {
            int cur = Integer.parseInt(values.get("count"));
            int add = items.get(0).getCount();
            values.replace("count", "" + (cur + add));
            update("cart", values);
        } else {
            insert("cart", values);
        }
    }

    static void insertItem(Map<String, String> values) {
        Statement stmt = getStatement();
        String condition = "itemID=" + values.get("itemID");
        List<ItemDTO> items = selectItemConditional(condition);

        if(items.size() > 0) {
            update("store", values);
        } else {
            insert("store", values);
        }
    }

    static void delete(String table, String con) {
        Statement stmt = getStatement();
        String deleteSql = "DELETE FROM " + table + " where " + con;
        try {
            System.out.println(deleteSql);
            stmt.executeUpdate(deleteSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
