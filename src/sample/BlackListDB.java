package sample;

import java.sql.*;

public class BlackListDB {
    private static Connection connection;
    private static Statement statement;

    //проба пера подключения к базе данных черного списка, но не выходит сделать это в ConsoleServer
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static String infoBlacklistBD ( String nickFrom) {
        String query = String.format("select nickTo from blacklist where nickFrom='%s'",  nickFrom);
        try {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()){
                rs.getString("nickTo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
