package edu.dnu.fpm.pz;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class Service {
    public static Connection getConnection() throws SQLException, MyException {
        Properties props = new Properties();
        try (InputStream input = Service.class.getClassLoader()
                .getResourceAsStream("db/db.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new MyException("class Service method getConnection()", e);
        }
        System.setProperty("driver", props.getProperty("driver"));
        try {
            Class.forName(props.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new MyException("class Service method getConnection()", e);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }
}
