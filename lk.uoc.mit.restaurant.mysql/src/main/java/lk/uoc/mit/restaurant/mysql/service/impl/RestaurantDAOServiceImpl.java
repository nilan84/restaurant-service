package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.model.Location;
import lk.uoc.mit.restaurant.mysql.model.User;
import lk.uoc.mit.restaurant.mysql.service.RestaurantDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantDAOServiceImpl  implements RestaurantDAOService {
   //https://dimitrisli.wordpress.com/2011/05/07/spring-mysql-jdbctemplate-demonstration/

    @Autowired
    DataSource dataSource;

    @Override
    public  List<User> getRestaurant() {
        System.out.println("test");
        String sql = "SELECT * FROM user";
        List userList = new ArrayList();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userList = jdbcTemplate.queryForList(sql);
        return userList;
    }

    @Override
    public long addLocation(final Location location){
        final String sql="INSERT INTO Gps_Tracker (Latitude,Longitude,Mob_No,date_time)" +
                " VALUES (?,?,?,now());";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,location.getLatitude());
                ps.setString(2,location.getLongitude());
                ps.setString(3,location.getMac());

                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();


    }


}
