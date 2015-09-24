package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.config.OrderStatus;
import lk.uoc.mit.restaurant.mysql.config.ReportConfig;
import lk.uoc.mit.restaurant.mysql.model.Order;
import lk.uoc.mit.restaurant.mysql.service.FoodDAOService;
import lk.uoc.mit.restaurant.mysql.service.PaymetDAOService;
import lk.uoc.mit.restaurant.mysql.service.ReportDAOService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 9/21/15.
 */
@Controller
public class ReportController {
    @Autowired
    ReportDAOService reportDAOService;
    @Autowired
    FoodDAOService foodDAOService;
    @Autowired
    ReportConfig reportConfig;
    @Autowired
    PaymetDAOService paymetDAOService;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportPage(Model model) {
        return "admin/Report";
    }

    @RequestMapping(value = "/getreport", method = RequestMethod.GET)
    public String customerPage(Model model) {
        reportDAOService.getReport();
        return "admin/Report";

    }

    @RequestMapping(value = "/pastmovereport", method = RequestMethod.GET)
    public String pastmove(Model model) {
        reportDAOService.getFastMoveReport();
        return "admin/Report";

    }

    @RequestMapping(value = "/slowmovereport", method = RequestMethod.GET)
    public String slowmove(Model model) {
        reportDAOService.getSlowMoveReport();
        return "admin/Report";

    }

    @RequestMapping(value = "/guestbill", method = RequestMethod.GET)
    public String gestBill(HttpServletResponse response,Model model,@RequestParam("id") Long orderId,@ModelAttribute("order")Order order) {

 try {
     InputStream input = new FileInputStream(new File("/home/nilan/Software/Axiata/Project/restaurant-service/jrxml/report2.jrxml"));
     Map<String, Object> params = new HashMap<String, Object>();
     params.put("orderId",orderId.toString());
     params.put("sum",paymetDAOService.getOrderAmountByOderId(orderId.toString()));
     JasperDesign jasperDesign = JRXmlLoader.load(input);
     JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, reportConfig.getConnection());
     JasperExportManager.exportReportToPdfFile(jasperPrint, "sample.pdf");
     String reportDestination = "sample.pdf";

     FileInputStream fis = new FileInputStream(new File(reportDestination));

     // Fast way to copy a bytearray from InputStream to OutputStream
     org.apache.commons.io.IOUtils.copy(fis, response.getOutputStream());
     response.setContentType("application/pdf");
     response.setHeader("Content-Disposition", "attachment; filename=" + reportDestination);
     response.flushBuffer();

 }catch (Exception ex)     {ex.printStackTrace();}

        List<Order> orderList=foodDAOService.getAllActiveOrder();
        model.addAttribute("enumValues", OrderStatus.values());
        model.addAttribute("orderList", orderList);
        return "admin/ViewOrder";

    }


    @RequestMapping(value = "/salesreport", method = RequestMethod.GET)
    public void gestSaselReport(HttpServletResponse response,Model model,@RequestParam("date") String date,@ModelAttribute("order")Order order) {

        try {
            InputStream input = new FileInputStream(new File("/home/nilan/Software/Axiata/Project/restaurant-service/jrxml/SaselReport.jrxml"));
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("dateVal",date);
            JasperDesign jasperDesign = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, reportConfig.getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "sample.pdf");
            String serverHomeDir = System.getenv("CATALINA_HOME");

            String reportDestination = "sample.pdf";

            FileInputStream fis = new FileInputStream(new File(reportDestination));

            // Fast way to copy a bytearray from InputStream to OutputStream
            org.apache.commons.io.IOUtils.copy(fis, response.getOutputStream());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + reportDestination);
            response.flushBuffer();

          //  Desktop.getDesktop().open(new File("sample.pdf"));

        }catch (Exception ex)     {ex.printStackTrace();}

       // List<Order> orderList=foodDAOService.getAllOrderByDate(date);
       // model.addAttribute("enumValues", OrderStatus.values());
      //  model.addAttribute("orderList", orderList);
       // return "admin/ViewOrder";

    }
}
