package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.model.Customer;
import lk.uoc.mit.restaurant.mysql.model.DashboardObject;
import lk.uoc.mit.restaurant.mysql.service.DashboardDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 2/5/16.
 */

@Repository
public class DashboardDAOServiceImpl implements DashboardDAOService{

    @Autowired
    private DataSource dataSource;
//select Food_type_id_l2,count(*) as foodCount from Order_Item,Food_Item where Order_Item.Food_no=Food_Item.Food_Item_id Group by Food_type_id_l2
public DashboardObject getFoodAndBevCount(){
    DashboardObject dashboardObject=new DashboardObject();
    String sql = "select Food_type_id_l2,count(*) as foodCount from Order_Item,Food_Item where Order_Item.Food_no=Food_Item.Food_Item_id Group by Food_type_id_l2";
    List<Customer> customers = new ArrayList<Customer>();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
    for (Map row : rows) {
        if(Integer.parseInt(row.get("Food_type_id_l2").toString())==2) {
            dashboardObject.setBeverageCount(Double.parseDouble(row.get("foodCount").toString()));
        }else {
            dashboardObject.setFoodCount(Double.parseDouble(row.get("foodCount").toString()));
        }

    }


    //set fast move items
    String sqlFastMove = "select FoodscanCode,Food_Item.Food_item_name as name,count(Food_No) as count from Order_Item,Food_Item where Order_Item.Food_no=Food_Item.Food_Item_id group by Food_No order by count DESC LIMIT 5;";
    List<Map<String, Object>> rowsFastMove = jdbcTemplate.queryForList(sqlFastMove);
    HashMap<String,String> fastMoveItem=new HashMap<String,String>();
    for (Map row : rowsFastMove) {
        fastMoveItem.put(row.get("name").toString(),row.get("count").toString());

    }
    dashboardObject.setFastMoveItem(fastMoveItem);


    //set slow move items
    String sqlshowMove = "select FoodscanCode,Food_Item.Food_item_name as name,count(Food_No) as count from Order_Item,Food_Item where Order_Item.Food_no=Food_Item.Food_Item_id group by Food_No order by count ASC LIMIT 5;";
    List<Map<String, Object>> rowsSlowMove = jdbcTemplate.queryForList(sqlshowMove);
    HashMap<String,String> slowMoveItem=new HashMap<String,String>();
    for (Map row : rowsSlowMove) {
        slowMoveItem.put(row.get("name").toString(),row.get("count").toString());

    }
    dashboardObject.setShowMoveItem(slowMoveItem);


    //payment methed
    String sqlPayType = "select Payment_type,sum(amount) as sum_amount from Payment group by Payment_type";
    List<Map<String, Object>> rowsPayType = jdbcTemplate.queryForList(sqlPayType);
    HashMap<String,String> payType=new HashMap<String,String>();
    for (Map row : rowsPayType) {
        payType.put(row.get("Payment_type").toString(),row.get("sum_amount").toString());

    }
    dashboardObject.setPaymentType(payType);



    return dashboardObject;
}




}
