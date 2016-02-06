package lk.uoc.mit.service.controller;

import com.googlecode.charts4j.*;
import lk.uoc.mit.restaurant.mysql.config.UserType;
import lk.uoc.mit.restaurant.mysql.model.DashboardObject;
import lk.uoc.mit.restaurant.mysql.service.DashboardDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by nilan on 10/11/15.
 */
@Controller
public class DashboardContoller {

    @Autowired
    DashboardDAOService dashboardDAOService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String customerPage(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("usertype") != null && httpSession.getAttribute("usertype") == UserType.Admin) {
            DashboardObject dashboardObject = dashboardDAOService.getFoodAndBevCount();

            Slice s1 = Slice.newSlice(dashboardObject.getFoodCount(), Color.newColor("CACACA"), " Food", " Food Items-"+dashboardObject.getFoodCount());
            Slice s2 = Slice.newSlice(dashboardObject.getBeverageCount(), Color.newColor("DF7417"), " Beverage", " Beverage Items-"+dashboardObject.getBeverageCount());
            PieChart pieChart = GCharts.newPieChart(s1, s2);
            pieChart.setTitle("Food Beverage Sales ", Color.BLACK, 15);
            pieChart.setSize(400, 200);
            pieChart.setThreeD(true);
            model.addAttribute("pieUrl", pieChart.toURLString());

            BarChartPlot data1 = Plots.newBarChartPlot(Data.newData(35, 50, 30, 40), Color.newColor("951800"), "Q1");
            BarChartPlot data2 = Plots.newBarChartPlot(Data.newData(10, 20, 30, 25), Color.newColor("01A1DB"), "Q2");
            BarChart chart = GCharts.newBarChart(data1, data2);
            chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("2012", "2013", "2014", "2015"));
            chart.addYAxisLabels((AxisLabelsFactory.newAxisLabels("LKR0", "LKR100")));
            chart.setTitle("Quarterly Revenue|(in billions of LKR)", Color.BLACK, 15);
            chart.setSize(400, 200);
            model.addAttribute("barUrl", chart.toURLString());




            HashMap<String,String> fastMoveItemList =dashboardObject.getFastMoveItem();
            String itemName[] = new String[5];
            Double itemCount[]=new Double[5];
            int i=0;
            for (String key : fastMoveItemList.keySet()) {
                itemName[i]=key;
                itemCount[i]=Double.parseDouble(fastMoveItemList.get(key));
                i++;
            }

            BarChartPlot pastMove = Plots.newBarChartPlot(Data.newData(itemCount[0], itemCount[1], itemCount[2], itemCount[3], itemCount[4]), Color.newColor("951800"), "Q1" );

            BarChart chartPastMove = GCharts.newBarChart(pastMove);
            chartPastMove.addXAxisLabels(AxisLabelsFactory.newAxisLabels(itemName[0].substring(0,5),itemName[1].substring(0,5), itemName[2].substring(0,5),itemName[3].substring(0,5),itemName[4].substring(0,5)));
            chartPastMove.addYAxisLabels((AxisLabelsFactory.newAxisLabels("0", "100")));

            chartPastMove.setTitle("Top Five Past Move Item", Color.BLACK, 15);
            chartPastMove.setSize(400, 200);
            chartPastMove.setSpaceBetweenGroupsOfBars(10);

            model.addAttribute("barPastMoveUrl", chartPastMove.toURLString());

            HashMap<String,String> showMoveItemList =dashboardObject.getShowMoveItem();
            String sitemName[] = new String[5];
            Double sitemCount[]=new Double[5];
            int si=0;
            for (String key : showMoveItemList.keySet()) {
                sitemName[si]=key;
                sitemCount[si]=Double.parseDouble(showMoveItemList.get(key));
                si++;
            }


            BarChartPlot slowMove = Plots.newBarChartPlot(Data.newData(sitemCount[0], sitemCount[1], sitemCount[2], sitemCount[3], sitemCount[4]), Color.newColor("DF7417"), "Q1");
            BarChart chartSlowMove = GCharts.newBarChart(slowMove);
            chartSlowMove.addXAxisLabels(AxisLabelsFactory.newAxisLabels(sitemName[0].substring(0,5),sitemName[1].substring(0,5), sitemName[2].substring(0,5),sitemName[3].substring(0,5),sitemName[4].substring(0,5)));
            chartSlowMove.addYAxisLabels((AxisLabelsFactory.newAxisLabels("0", "100")));
            chartSlowMove.setTitle("Top Slow Move Item", Color.BLACK, 15);
            chartSlowMove.setSize(400, 200);
            model.addAttribute("barSlowMoveUrl", chartSlowMove.toURLString());




            Plot plot = Plots.newPlot(Data.newData(10, 20, 10, 15, 30, 45, 50));
            LineChart lineChart = GCharts.newLineChart(plot);
            //lineChart.addHorizontalRangeMarker(33.3,66.6,Color.newColor("951800"));
            lineChart.setGrid(33.3, 33.3, 3, 3);
            lineChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(Arrays.asList("Sun", "Mon", "Tue", "Wen", "Thu", "Fir", "Sat"), Arrays.asList(14, 28, 42, 56, 70, 84, 98)));
            lineChart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(0, 20, 40, 60, 80, 100));
            lineChart.setSize(400, 200);
            lineChart.setTitle("Last Week Revenue ", Color.BLACK, 15);
            model.addAttribute("lineUrl", lineChart.toURLString());

            Slice s11 = Slice.newSlice(Integer.parseInt(dashboardObject.getPaymentType().get("0").replace(".0","")), Color.newColor("CACACA"), "Cash", "Cash - "+dashboardObject.getPaymentType().get("0").replace(".0",""));
            Slice s12 = Slice.newSlice(Integer.parseInt(dashboardObject.getPaymentType().get("1").replace(".0", "")), Color.newColor("DF7417"), "Visa Card", "Visa Card - "+dashboardObject.getPaymentType().get("1").replace(".0",""));
            Slice s13 = Slice.newSlice(Integer.parseInt(dashboardObject.getPaymentType().get("3").replace(".0", "")), Color.newColor("951800"), "Mobile Cash", "Mobile Cash - "+dashboardObject.getPaymentType().get("3").replace(".0",""));
            Slice s14 = Slice.newSlice(Integer.parseInt(dashboardObject.getPaymentType().get("2").replace(".0", "")), Color.newColor("01A1DB"), "Master Card", "Master Card - "+dashboardObject.getPaymentType().get("2").replace(".0",""));
            PieChart pieChart2 = GCharts.newPieChart(s11, s12, s13, s14);
            pieChart2.setTitle("Payment Method", Color.BLACK, 15);
            pieChart2.setSize(400, 200);
            pieChart2.setThreeD(false);
            model.addAttribute("pie2Url", pieChart2.toURLString());


            return "admin/Dashbord";
        } else {
            return "admin/Unauthorized";
        }


    }
}
