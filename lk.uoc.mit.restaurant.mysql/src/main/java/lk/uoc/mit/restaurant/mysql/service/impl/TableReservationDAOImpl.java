package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.config.TableOrderTime;
import lk.uoc.mit.restaurant.mysql.model.Customer;
import lk.uoc.mit.restaurant.mysql.model.Table;
import lk.uoc.mit.restaurant.mysql.model.TableReservation;
import lk.uoc.mit.restaurant.mysql.service.TableReservationDAO;
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
 * Created by nilan on 9/24/15.
 */
@Repository
public class TableReservationDAOImpl implements TableReservationDAO {
    @Autowired
    private DataSource dataSource;

    public long addTableReservation(final TableReservation tableReservation){

        final String sql="INSERT INTO Table_Reservation (table_no,date_time,status,cust_id,order_time)" +
                " VALUES (?,?,?,?);";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,tableReservation.getTable().getTableNo());
                ps.setString(2,tableReservation.getDate());
                ps.setInt(3,1);
                ps.setInt(4,tableReservation.getCustomer().getCustomerId());
                ps.setInt(3,tableReservation.getTableOrderTime().ordinal());
                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void chnageTime(long reservationNo,TableOrderTime orderTime){


    }

    public void chnageStatus(long reservationNo,int status){


    }

    public TableReservation getTableReservationbyId(long resNo){
        TableReservation tableReservation=new TableReservation();

        return tableReservation;
    }


    public List<TableReservation> getTableReservation(String datetime){

        String sql = "SELECT * FROM Table_Reservation where date_time='"+datetime+"'";
        List<TableReservation> tableReservations = new ArrayList<TableReservation>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Customer customer=new Customer();
            Table table=new Table();
            TableReservation tableReservation=new TableReservation();
            customer.setCustomerId(Integer.parseInt(row.get("Food_Item_id").toString()));
            table.setTableNo(Integer.parseInt(row.get("table_no").toString()));
            tableReservation.setCustomer(customer);
            tableReservation.setTable(table);
            tableReservation.setDate(row.get("date_time").toString());
            tableReservation.setTableOrderTime(TableOrderTime.values()[(Integer.parseInt(row.get("order_time").toString()))]);

            tableReservations.add(tableReservation);
        }

        return tableReservations;

    }

    public List<Table> getAllTable(){

        String sql = "SELECT * FROM Table_master ";
        List<Table> tables = new ArrayList<Table>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
           Table table=new Table();
            table.setTableNo(Integer.parseInt(row.get("Table_id").toString()));
            table.setTableName(row.get("Table_name").toString());
         }

        return tables;
    }
}
