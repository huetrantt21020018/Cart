package org.example.cart;

import org.example.cart.Util.Item;
import org.example.cart.Util.Order;

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

    static List<Item> select(String table) {
        Statement stmt = getStatement();

        String selectSql = "SELECT * from " + table;
        List<Item> items= new ArrayList<>();
        try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
            while (resultSet.next()) {
                items.add(new Item(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    static List<Order> joinSelect(String table1, String table2, String condition) {
        Statement stmt = getStatement();

        String selectSql = "SELECT * from " + table1 + " join " + table2 + " on " + condition;
        List<Order> items= new ArrayList<>();
        try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
            while (resultSet.next()) {
                items.add(new Order(resultSet.getInt(1), resultSet.getString(4), resultSet.getInt(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    static List<Order> selectConditional(String table, String condition) {
        Statement stmt = getStatement();
        String selectSql = "SELECT * from " + table + " where " + condition;
        List<Order> items= new ArrayList<>();
        
        try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
            while (resultSet.next()) {
                items.add(new Order(resultSet.getInt(1), null, resultSet.getInt(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    static void insert(String table, Map<String, String> values) {
        Statement stmt = getStatement();
        String condition = "itemID=" + values.get("itemID");
        List<Order> items = selectConditional(table, condition);

        if(items.size() > 0) {
            String updateSql = "update " + table + " set ";
            int cur = Integer.parseInt(values.get("count"));
            int add = items.get(0).getCount();
            values.replace("count", "" + (cur + add));

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
        } else {
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
