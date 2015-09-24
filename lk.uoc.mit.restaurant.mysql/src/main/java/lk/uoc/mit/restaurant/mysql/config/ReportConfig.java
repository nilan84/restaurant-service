package lk.uoc.mit.restaurant.mysql.config;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by nilan on 9/21/15.
 */
@Service
public class ReportConfig {

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restaurant", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
            //return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //  return;
        }
        return connection;
    }

}
