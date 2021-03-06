package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.config.FoodType;
import lk.uoc.mit.restaurant.mysql.config.OrderStatus;
import lk.uoc.mit.restaurant.mysql.config.UserType;
import lk.uoc.mit.restaurant.mysql.model.*;
import lk.uoc.mit.restaurant.mysql.service.FoodDAOService;
import lk.uoc.mit.restaurant.mysql.service.PaymetDAOService;
import lk.uoc.mit.restaurant.mysql.service.RestaurantDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 8/21/15.
 */
@Repository
public class FoodDAOServiceImpl implements FoodDAOService {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PaymetDAOService paymentDAOService;

    @Autowired
    private RestaurantDAOService restaurantDAOService;



    public FoodDAOServiceImpl() {
    }

    @Override
    public List<Food> getAllFood() {

        String sql = "SELECT * FROM Food_Item";
        List<Food> food = new ArrayList<Food>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Food fooditem=new Food();
            fooditem.setFoodNo(Integer.parseInt(row.get("Food_Item_id").toString()));
            fooditem.setFoodName(row.get("Food_item_name").toString());
            fooditem.setFoodPrice(Double.parseDouble(row.get("Food_Price").toString()));
            food.add(fooditem);
        }

        return food;

    }

    @Override
    public List<Order> getAllActiveOrder(HttpSession httpSession) throws Exception{
        String sql="";

        if(httpSession.getAttribute("usertype")==UserType.Mobile ||httpSession.getAttribute("usertype")==UserType.Web ) {
             sql = "SELECT * FROM Order_master,Customer where Order_master.customer_Id='"+httpSession.getAttribute("customer_Id")+"' and Order_master.customer_Id=Customer.customer_id and status='" + OrderStatus.Confirm.ordinal() + "'";
        }else if(httpSession.getAttribute("usertype")==UserType.Admin || httpSession.getAttribute("usertype")==UserType.Waiter){
            sql = "SELECT * FROM Order_master,Customer where Order_master.customer_Id=Customer.customer_id and status='" + OrderStatus.Confirm.ordinal() + "'";
        }

        List<Order> orders = new ArrayList<Order>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Customer customer=new Customer();
            Order order=new Order();
            order.setOrderNo(Integer.parseInt(row.get("Order_id").toString()));
            order.setDescription(row.get("Description").toString());
            order.setOrderStatus(OrderStatus.values()[Integer.parseInt(row.get("status").toString())]);
            customer.setCustomerName(row.get("cus_name").toString());
            customer.setCustomerEmail(row.get("email").toString());
            order.setCustomer(customer);
            order.setOrderAmount(paymentDAOService.getOrderAmountByOderId(row.get("Order_id").toString()));
            GpsTracker gpsTracker=restaurantDAOService.getMaxGPS(order.getOrderNo());
            order.setLat(gpsTracker.getLat());
            order.setLng(gpsTracker.getLog());
            orders.add(order);
        }

        return orders;

    }


    @Override
    public List<Order> getAllOnDeliveryOrder(HttpSession httpSession) throws Exception{
        String sql="";

        if(httpSession.getAttribute("usertype")==UserType.Mobile ||httpSession.getAttribute("usertype")==UserType.Web ) {
            sql = "SELECT * FROM Order_master,Customer where Order_master.customer_Id='"+httpSession.getAttribute("customer_Id")+"' and Order_master.customer_Id=Customer.customer_id and status='" + OrderStatus.Delivering.ordinal() + "'";
        }else if(httpSession.getAttribute("usertype")==UserType.Admin || httpSession.getAttribute("usertype")==UserType.Waiter){
            sql = "SELECT * FROM Order_master,Customer where Order_master.customer_Id=Customer.customer_id and status='" + OrderStatus.Delivering.ordinal() + "'";
        }

        List<Order> orders = new ArrayList<Order>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Customer customer=new Customer();
            Order order=new Order();
            order.setOrderNo(Integer.parseInt(row.get("Order_id").toString()));
            order.setDescription(row.get("Description").toString());
            order.setOrderStatus(OrderStatus.values()[Integer.parseInt(row.get("status").toString())]);
            customer.setCustomerName(row.get("cus_name").toString());
            customer.setCustomerEmail(row.get("email").toString());
            order.setCustomer(customer);
            order.setOrderAmount(paymentDAOService.getOrderAmountByOderId(row.get("Order_id").toString()));
            GpsTracker gpsTracker=restaurantDAOService.getMaxGPS(order.getOrderNo());
            order.setLat(gpsTracker.getLat());
            order.setLng(gpsTracker.getLog());
            orders.add(order);
        }

        return orders;

    }

    @Override
    public List<Order> getAllOrderByDate(String date) {
        String sql="";
        String fromDate=date+" 00:00:00";
        String toDate=date+" 23:59:59";
        sql = "SELECT * FROM Order_master,Customer where Order_master.customer_Id=Customer.customer_id and status!=0 and update_time  between'" + fromDate + "' and '"+toDate+"'";

        List<Order> orders = new ArrayList<Order>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Customer customer=new Customer();
            Order order=new Order();
            order.setOrderNo(Integer.parseInt(row.get("Order_id").toString()));
            order.setDescription(row.get("Description").toString());
            order.setOrderStatus(OrderStatus.values()[Integer.parseInt(row.get("status").toString())]);
            customer.setCustomerName(row.get("cus_name").toString());
            customer.setCustomerEmail(row.get("email").toString());
            order.setCustomer(customer);
            order.setOrderAmount(paymentDAOService.getOrderAmountByOderId(row.get("Order_id").toString()));
            orders.add(order);
        }

        return orders;

    }


    @Override
    public Long addFoodItem(final Food food) {

        final String sql="INSERT INTO Food_Item (Food_type_id_l2,Food_item_name,FoodscanCode,Food_Price,helth_discription,status,image)" +
                " VALUES (?,?,?,?,?,?,?);";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,food.getFoodtype().ordinal());
                ps.setString(2,food.getFoodName());
                ps.setString(3,food.getFoodscanCode());
                ps.setDouble(4,food.getFoodPrice());
                ps.setString(5,food.getFoodDiscription());
                ps.setInt(6,1);
                ps.setBytes(7,food.getItemImage());
                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();
    }


    @Override
    public Long editFoodItem(final Food food){
        final String sql="update Food_Item set Food_type_id_l2=?,Food_item_name=?,FoodscanCode=?,Food_Price=?,helth_discription=? where Food_Item_id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setInt(1,food.getFoodtype().ordinal());
                ps.setString(2,food.getFoodName());
                ps.setString(3,food.getFoodscanCode());
                ps.setDouble(4,food.getFoodPrice());
                ps.setString(5,food.getFoodDiscription());
                ps.setInt(6,food.getFoodNo());
                return ps;
            }
        });
        return null;
    }



    @Override
    public Long addOrderItem(final OrderFood orderFood) {
        final String sql="INSERT INTO Order_Item (oder_No,Food_no,unit_price,total_Price,no_of_unit,description)" +
                " VALUES (?,?,?,?,?,?);";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1,orderFood.getOrderNo());
                ps.setInt(2,orderFood.getFoodId());
                ps.setDouble(3,orderFood.getUnitPrice());
                ps.setDouble(4,(orderFood.getUnitPrice()*orderFood.getNoOfitem()));
                ps.setInt(5,orderFood.getNoOfitem());
                ps.setString(6,orderFood.getDescription());
                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Long addOrder(final Order order) {
        final String sql="INSERT INTO Order_master (Description,customer_Id,status,update_time)" +
                " VALUES (?,?,?,now());";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,order.getDescription());
                ps.setInt(2,order.getCustomer().getCustomerId());
                ps.setInt(3,OrderStatus.Pending.ordinal());
                return ps;
            }
        },keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public List<OrderFood> getOrderFood(long orderNo) {
        String sql = "select * from Order_Item,Food_Item where Order_Item.Food_no=Food_Item.Food_Item_id and oder_No='"+orderNo+"'";
        List<OrderFood> orders = new ArrayList<OrderFood>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            OrderFood order=new OrderFood();
            Food food=new Food();
            order.setUnitPrice(Double.parseDouble(row.get("unit_price").toString()));
            order.setNoOfitem(Integer.parseInt(row.get("no_of_unit").toString()));
            order.setFoodId(Integer.parseInt(row.get("Food_no").toString()));
            food.setFoodName(row.get("Food_item_name").toString());

            order.setFood(food);
             orders.add(order);
        }

        return orders;
    }

    @Override
    public Food getFoodById(int foodId){
        String sql = "SELECT * FROM Food_Item where Food_Item_id='"+foodId+"'";
        Food fooditem = new Food();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            fooditem.setFoodNo(Integer.parseInt(row.get("Food_Item_id").toString()));
            fooditem.setFoodName(row.get("Food_item_name").toString());
            fooditem.setFoodPrice(Double.parseDouble(row.get("Food_Price").toString()));
            fooditem.setFoodDiscription(row.get("helth_discription").toString());
            fooditem.setFoodscanCode(row.get("FoodscanCode").toString());

            int enamIntVal=Integer.parseInt(row.get("Food_type_id_l2").toString());
            FoodType foodType=FoodType.NO_Type;;
            if(enamIntVal==0){foodType=FoodType.NO_Type;}
            else if(enamIntVal==1){foodType=FoodType.Food;}
            else if(enamIntVal==2){foodType=FoodType.Beverage;}
            fooditem.setFoodtype(foodType);

            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            byte[] bytes=null;
            try {
                ObjectOutputStream oos = new ObjectOutputStream(bytesOut);
                oos.writeObject(row.get("image"));
                oos.flush();
                bytes = bytesOut.toByteArray();
                bytesOut.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            fooditem.setItemImage(bytes);

        }
        return fooditem;

    }

    public void changeOrderStatus(final long orderNo,OrderStatus orderStatus){
        final int status=orderStatus.ordinal();
        final String sql="update Order_master set status=? where Order_id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setInt(1,status);
                ps.setLong(2,orderNo);
                return ps;
            }
        });
    }


    public void deleteOrderItem(final long orderNo,final int foodNo){
        final String sql="delete from Order_Item where oder_No=? and Food_no=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setLong(1,orderNo);
                ps.setInt(2,foodNo);
                return ps;
            }
        });

    }

    public Order getOrderById(Long orderId){
        Order order=new Order();
        String sql = "SELECT * FROM Order_master,Customer where Order_master.customer_Id=Customer.customer_id and Order_id='"+orderId+"'";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Customer customer=new Customer();
            order.setOrderNo(Integer.parseInt(row.get("Order_id").toString()));
            order.setDescription(row.get("Description").toString());
            order.setOrderStatus(OrderStatus.values()[Integer.parseInt(row.get("status").toString())]);
            customer.setCustomerName(row.get("cus_name").toString());
            customer.setCustomerEmail(row.get("email").toString());
            order.setCustomer(customer);
            order.setOrderAmount(paymentDAOService.getOrderAmountByOderId(row.get("Order_id").toString()));

        }

        return order;

    }

    public Order getOrderByCusId(Long cusId){
        Order order=new Order();
        String sql = "SELECT * FROM Order_master,Customer where Order_master.customer_Id=Customer.customer_id and Order_master.customer_Id='"+cusId+"' and status=1 order by Order_id";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Customer customer=new Customer();
            order.setOrderNo(Integer.parseInt(row.get("Order_id").toString()));
            order.setDescription(row.get("Description").toString());
            order.setOrderStatus(OrderStatus.values()[Integer.parseInt(row.get("status").toString())]);
            customer.setCustomerName(row.get("cus_name").toString());
            customer.setCustomerEmail(row.get("email").toString());
            order.setCustomer(customer);
            order.setOrderAmount(paymentDAOService.getOrderAmountByOderId(row.get("Order_id").toString()));

        }

        return order;



    }
}
