package lk.gym.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private Connection connection;
    private static ConnectionFactory dbConnection;

    public ConnectionFactory(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        Properties dbProperties=new Properties();
//        File dbFile=new File("settings/dbSettings.properties");
//        try {
//            FileReader dbReader=new FileReader(dbFile);
//            dbProperties.load(dbReader);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String url = "jdbc:mysql://localhost/gymnew";
        String userName = "root";
        String password = "root";

        try {
            connection= DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public static ConnectionFactory getInstance(){
        if(dbConnection==null){
            dbConnection=new ConnectionFactory();
        }
        return dbConnection;
    }
}
