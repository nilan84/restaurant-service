package lk.uoc.mit.restaurant.mysql.model;

/**
 * Created by nilan on 9/24/15.
 */
public class Table {
    private int tableNo;
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }


}
