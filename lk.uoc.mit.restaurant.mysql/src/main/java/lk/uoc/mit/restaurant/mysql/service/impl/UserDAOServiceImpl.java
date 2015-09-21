package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.config.UserType;
import lk.uoc.mit.restaurant.mysql.model.User;
import lk.uoc.mit.restaurant.mysql.service.UserDAOService;
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

/**
 * Created by nilan on 9/20/15.
 */
@Repository
public class UserDAOServiceImpl implements UserDAOService {

    @Autowired
    DataSource dataSource;

    public List<User> getAllUser(){
        String sql = "SELECT * FROM user";
        List<User> userList = new ArrayList<User>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            User user=new User();
            user.setUserId(Integer.parseInt(row.get("user_id").toString()));
            user.setUsername(row.get("user_name").toString());
            user.setPassward(row.get("passward").toString());
            user.setFirstName(row.get("first_name").toString());
            user.setLastName(row.get("last_name").toString());
            user.setUserType(UserType.values()[(Integer.parseInt(row.get("user_type").toString()))]);
            userList.add(user);
        }

        return userList;
    }

    public long addUser(final User user){
        final String sql="INSERT INTO user (user_name,passward,first_name,last_name,user_type)" +
                " VALUES (?,?,?,?,?);";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassward());
                ps.setString(3,user.getFirstName());
                ps.setString(4, user.getLastName());
                ps.setInt(5, user.getUserType().ordinal());
                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void editUser(final User user){
        final String sql="update user set passward=?,first_name=?,last_name=?,user_type=? where user_id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1, user.getPassward());
                ps.setString(2, user.getLastName());
                ps.setString(3,user.getFirstName());
                ps.setInt(4, user.getUserType().ordinal());
                ps.setInt(5, user.getUserId());

                return ps;
            }
        });}

    public User getUserById(int userId){
        String sql = "SELECT * FROM user where user_id='"+userId+"'";
        User user = new User();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            user.setUserId(Integer.parseInt(row.get("user_id").toString()));
            user.setUsername(row.get("user_name").toString());
            user.setPassward(row.get("passward").toString());
            user.setFirstName(row.get("first_name").toString());
            user.setLastName(row.get("last_name").toString());
            user.setUserType(UserType.values()[(Integer.parseInt(row.get("user_type").toString()))]);
        }

        return user;

    }
}
