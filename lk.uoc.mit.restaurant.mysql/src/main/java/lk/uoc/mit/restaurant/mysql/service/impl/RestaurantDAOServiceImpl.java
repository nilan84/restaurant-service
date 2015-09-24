package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.model.Location;
import lk.uoc.mit.restaurant.mysql.model.Restaurant;
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
import java.util.Map;

@Repository
public class RestaurantDAOServiceImpl  implements RestaurantDAOService {
   //https://dimitrisli.wordpress.com/2011/05/07/spring-mysql-jdbctemplate-demonstration/

    @Autowired
    DataSource dataSource;

    @Override
    public  List<Restaurant> getRestaurant() {
        String sql = "SELECT * FROM restaurant_location";
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Restaurant restaurant=new Restaurant();
            restaurant.setResturantId(Integer.parseInt(row.get("restaurantLocation_id").toString()));
            restaurant.setResturantName(row.get("res_name").toString());
            restaurant.setLatitude(row.get("lat").toString());
            restaurant.setLongitude(row.get("log").toString());
            restaurantList.add(restaurant);
        }

        return restaurantList;
    }

    @Override
    public long addLocation(final Location location){
        final String sql="INSERT INTO Gps_Tracker (Latitude,Longitude,Mob_No,order_No,date_time)" +
                " VALUES (?,?,?,?,now());";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,location.getLatitude());
                ps.setString(2,location.getLongitude());
                ps.setString(3,location.getMac());
                ps.setString(4,location.getOrderNo());


                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();


    }

    public void editRestaurant(final Restaurant restaurant){
        final String sql="update restaurant_location set res_name=?,lat=?,log=? where restaurantLocation_id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1, restaurant.getResturantName());
                ps.setString(2,restaurant.getLatitude());
                ps.setString(3,restaurant.getLatitude());
                ps.setInt(4,restaurant.getResturantId());
                return ps;
            }
        });

    }


    public Restaurant getRestaurantById(int restId){
        String sql = "SELECT * FROM restaurant_location where restaurantLocation_id='"+restId+"'";
        Restaurant restaurant = new Restaurant();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            restaurant.setResturantId(Integer.parseInt(row.get("restaurantLocation_id").toString()));
            restaurant.setResturantName(row.get("res_name").toString());
            restaurant.setLatitude(row.get("lat").toString());
            restaurant.setLongitude(row.get("log").toString());
        }

        return restaurant;

    }


}
