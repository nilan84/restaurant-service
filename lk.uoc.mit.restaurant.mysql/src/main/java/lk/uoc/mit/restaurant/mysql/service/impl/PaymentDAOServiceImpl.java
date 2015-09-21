package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.model.Food;
import lk.uoc.mit.restaurant.mysql.model.Payment;
import lk.uoc.mit.restaurant.mysql.service.PaymetDAOService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 8/23/15.
 */
@Repository
public class PaymentDAOServiceImpl implements PaymetDAOService{

    @Autowired
    private DataSource dataSource;

    @Override
    public double getOrderAmountByOderId(String orderId) {
        Double amount=0.0;
        String sql = "SELECT sum(total_Price) as sumamount FROM Order_Item where oder_No='"+orderId+"'";
        Food fooditem = new Food();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            amount=Double.parseDouble(row.get("sumamount").toString());

        }
        return amount;
    }

    public long addPaymet(final Payment payment){
        final String sql="INSERT INTO Paymet (Payment_type,order_no,amount)" +
                " VALUES (?,?,?);";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,payment.getPaymetType().ordinal());
                ps.setLong(2,payment.getOrderNo());
                ps.setDouble(3,payment.getAmount());
                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();

    }
}
