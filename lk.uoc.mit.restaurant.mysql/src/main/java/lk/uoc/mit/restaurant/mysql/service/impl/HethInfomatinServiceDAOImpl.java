package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.model.HelthInformation;
import lk.uoc.mit.restaurant.mysql.service.HethInfomationDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 8/19/15.
 */

@Repository
public class HethInfomatinServiceDAOImpl implements HethInfomationDAOService {

    @Autowired
    private DataSource dataSource;


    @Override
    public HelthInformation viewHethInfo(String barCode) {
        String sql = "SELECT * FROM Food_Item where FoodscanCode = '"+barCode+"'";
        HelthInformation helthInformation=new HelthInformation();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
           helthInformation.setDiscription(row.get("helth_discription").toString());
            helthInformation.setPrice(Double.parseDouble(row.get("Food_Price").toString()));
            helthInformation.setBarCode(row.get("FoodscanCode").toString());
            helthInformation.setFoodItemName(row.get("Food_item_name").toString());
            helthInformation.setFoodNo(Integer.parseInt(row.get("Food_Item_id").toString()));
        }

        return helthInformation;
    }
}
