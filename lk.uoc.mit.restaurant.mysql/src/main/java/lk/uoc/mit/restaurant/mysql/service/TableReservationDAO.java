package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.config.TableOrderTime;
import lk.uoc.mit.restaurant.mysql.model.Table;
import lk.uoc.mit.restaurant.mysql.model.TableReservation;

import java.util.List;

/**
 * Created by nilan on 9/24/15.
 */
public interface TableReservationDAO {

    public long addTableReservation(TableReservation tableReservation);
    public void chnageTime(long reservationNo,TableOrderTime orderTime);
    public void chnageStatus(long reservationNo,int status);
    public TableReservation getTableReservationbyId(long resNo);
    public List<TableReservation> getTableReservation(String datetime);
    public List<Table> getAllTable();


}
