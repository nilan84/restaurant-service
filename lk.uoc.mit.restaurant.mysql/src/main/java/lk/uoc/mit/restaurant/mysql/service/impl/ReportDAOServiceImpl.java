package lk.uoc.mit.restaurant.mysql.service.impl;

import lk.uoc.mit.restaurant.mysql.config.ReportConfig;
import lk.uoc.mit.restaurant.mysql.service.ReportDAOService;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;

/**
 * Created by nilan on 9/21/15.
 */
@Service
public class ReportDAOServiceImpl  implements ReportDAOService {
    @Autowired
    ReportConfig reportConfig;

    @Async
    public void getReport(){
        JasperReportBuilder report = DynamicReports.report();//a new report
        Connection connection=reportConfig.getConnection();
        report
                .columns(
                        Columns.column("Customer Id", "Customer_id", DataTypes.stringType()),
                        Columns.column("Customer Name", "cus_name", DataTypes.stringType()),
                        Columns.column("email", "email", DataTypes.stringType()),
                        Columns.column("MAC", "mac_address", DataTypes.stringType()))
                .title(//title of the report
                        Components.text("Customer Report")
                                .setHorizontalAlignment(HorizontalAlignment.CENTER))
                .pageFooter(Components.pageXofY())//show page number on the page footer
                .setDataSource("SELECT Customer_id, cus_name, email, mac_address FROM Customer",connection
                        );

        try {

            report.toDocx(new FileOutputStream("report.docx"));
            Desktop.getDesktop().open(new File("report.docx"));
            connection.close();




        } catch (DRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void getFastMoveReport(){
        Connection connection=reportConfig.getConnection();

        String sql="select FoodscanCode,Food_Item.Food_item_name as name,count(Food_No) as count from Order_Item,Food_Item where Order_Item.Food_no=Food_Item.Food_Item_id group by Food_No order by count DESC LIMIT 10;";
          JasperReportBuilder report = DynamicReports.report();//a new report

        report
                .columns(
                        Columns.column("Food Name", "name", DataTypes.stringType()),
                        Columns.column("Food Scan Code", "FoodscanCode", DataTypes.stringType()),
                        Columns.column("Count", "count",DataTypes.stringType()))
                .title(//title of the report
                        Components.text("Fast Move Report")
                                .setHorizontalAlignment(HorizontalAlignment.CENTER))
                .pageFooter(Components.pageXofY())//show page number on the page footer
                .setDataSource(sql,
                        connection);

        try {

            report.toPdf(new FileOutputStream("report.pdf"));
            Desktop.getDesktop().open(new File("report.pdf"));
            connection.close();



        } catch (DRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSlowMoveReport(){
        Connection connection=reportConfig.getConnection();

        String sql="select FoodscanCode,Food_Item.Food_item_name as name,count(Food_No) as count from Order_Item,Food_Item where Order_Item.Food_no=Food_Item.Food_Item_id group by Food_No order by count ASC LIMIT 10;";
        JasperReportBuilder report = DynamicReports.report();//a new report

        report
                .columns(
                        Columns.column("Food Name", "name", DataTypes.stringType()),
                        Columns.column("Food Scan Code", "FoodscanCode", DataTypes.stringType()),
                        Columns.column("Count", "count",DataTypes.stringType()))
                .title(//title of the report
                        Components.text("Slow Move Report")
                                .setHorizontalAlignment(HorizontalAlignment.CENTER))
                .pageFooter(Components.pageXofY())//show page number on the page footer
                .setDataSource(sql,
                        connection);

        try {

            report.toPdf(new FileOutputStream("report.pdf"));
            Desktop.getDesktop().open(new File("report.pdf"));
            connection.close();



        } catch (DRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }






}
