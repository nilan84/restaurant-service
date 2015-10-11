package lk.uoc.mit.service.controller;

import com.googlecode.charts4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

/**
 * Created by nilan on 10/11/15.
 */
@Controller
public class DashboardContoller {

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String customerPage(Model model) {

        Slice s1 = Slice.newSlice(45, Color.newColor("CACACA"), " Food", " Food");
        Slice s2 = Slice.newSlice(60, Color.newColor("DF7417"), " Beverage"," Beverage");
        PieChart pieChart = GCharts.newPieChart(s1, s2);
        pieChart.setTitle("Food Beverage Sales ", Color.BLACK, 15);
        pieChart.setSize(400, 200);
        pieChart.setThreeD(true);
        model.addAttribute("pieUrl", pieChart.toURLString());

        BarChartPlot data1 = Plots.newBarChartPlot(Data.newData(35, 50, 30, 40), Color.newColor("951800"),"Q1");
        BarChartPlot data2 = Plots.newBarChartPlot(Data.newData(10, 20, 30, 25), Color.newColor("01A1DB"), "Q2");
        BarChart chart = GCharts.newBarChart(data1, data2);
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("2012", "2013", "2014", "2015"));
        chart.addYAxisLabels((AxisLabelsFactory.newAxisLabels("LKR0", "LKR100")));
        chart.setTitle("Quarterly Revenue|(in billions of LKR)",Color.BLACK, 15);
        chart.setSize(400, 200);
        model.addAttribute("barUrl", chart.toURLString());


        Plot plot=Plots.newPlot(Data.newData(10,20,10,15,30,45,50));
        LineChart lineChart=GCharts.newLineChart(plot);
        //lineChart.addHorizontalRangeMarker(33.3,66.6,Color.newColor("951800"));
        lineChart.setGrid(33.3,33.3,3,3);
        lineChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(Arrays.asList("Sun", "Mon","Tue", "Wen","Thu", "Fir","Sat"),Arrays.asList(14,28,42,56,70,84,98)));
        lineChart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(0,20,40,60,80,100));
        lineChart.setSize(400, 200);
        lineChart.setTitle("Last Week Revenue ",Color.BLACK, 15);
        model.addAttribute("lineUrl", lineChart.toURLString());

        Slice s11 = Slice.newSlice(15, Color.newColor("CACACA"), "Cash", "Cash");
        Slice s12 = Slice.newSlice(50, Color.newColor("DF7417"), "Visa Card","Visa Card");
        Slice s13 = Slice.newSlice(25, Color.newColor("951800"), "Mobile Cash","Mobile Cash");
        Slice s14 = Slice.newSlice(10, Color.newColor("01A1DB"), "Master Card", "Master Card");
        PieChart pieChart2 = GCharts.newPieChart(s11, s12, s13, s14);
        pieChart2.setTitle("Payment Method", Color.BLACK, 15);
        pieChart2.setSize(400, 200);
        pieChart2.setThreeD(false);
        model.addAttribute("pie2Url", pieChart2.toURLString());

        BarChartPlot pastMove = Plots.newBarChartPlot(Data.newData(35, 50, 30, 40,50), Color.newColor("951800"),"Q1");

        BarChart chartPastMove = GCharts.newBarChart(pastMove);
        chartPastMove.addXAxisLabels(AxisLabelsFactory.newAxisLabels("2000", "2001", "2002", "2003","2004"));
        chartPastMove.addYAxisLabels((AxisLabelsFactory.newAxisLabels("0", "100")));
        chartPastMove.setTitle("Top Five Past Move Item",Color.BLACK, 15);
        chartPastMove.setSize(400, 200);
        model.addAttribute("barPastMoveUrl", chartPastMove.toURLString());

        BarChartPlot slowMove = Plots.newBarChartPlot(Data.newData(35, 50, 30, 40,60), Color.newColor("DF7417"),"Q1");
        BarChart chartSlowMove = GCharts.newBarChart(slowMove);
        chartSlowMove.addXAxisLabels(AxisLabelsFactory.newAxisLabels("2000", "2001", "2002", "2003","2004"));
        chartSlowMove.addYAxisLabels((AxisLabelsFactory.newAxisLabels("0", "100")));
        chartSlowMove.setTitle("Top Slow Move Item",Color.BLACK, 15);
        chartSlowMove.setSize(400, 200);
        model.addAttribute("barSlowMoveUrl", chartSlowMove.toURLString());




        return "admin/Dashbord";

    }
}
