package lk.uoc.mit.restaurant.mysql.model;

import lk.uoc.mit.restaurant.mysql.config.TableOrderTime;

/**
 * Created by nilan on 9/24/15.
 */
public class TableReservation {

    private long tableOrderNo;
    private Table table;
    private String date;
    private TableOrderTime tableOrderTime;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TableOrderTime getTableOrderTime() {
        return tableOrderTime;
    }

    public void setTableOrderTime(TableOrderTime tableOrderTime) {
        this.tableOrderTime = tableOrderTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public long getTableOrderNo() {
        return tableOrderNo;
    }

    public void setTableOrderNo(long tableOrderNo) {
        this.tableOrderNo = tableOrderNo;
    }


}
