package lk.uoc.mit.service.webservice;

import lk.uoc.mit.restaurant.mysql.model.HelthInformation;
import lk.uoc.mit.restaurant.mysql.service.HethInfomationDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nilan on 8/18/15.
 */
@Controller
public class FoodService {

    @Autowired
    HethInfomationDAOService hethInfomationDAOService;

    @RequestMapping(value = "/view/foodinfo/{barcode}", method = RequestMethod.GET)
    public @ResponseBody
    HelthInformation viewHethInformation(@PathVariable String barcode,ModelMap model){
        return hethInfomationDAOService.viewHethInfo(barcode);
    }


    @RequestMapping(value = "/food/add/{barcode}", method = RequestMethod.GET)
    public @ResponseBody
    HelthInformation addScanFood(@PathVariable String barcode,ModelMap model){
        return hethInfomationDAOService.viewHethInfo(barcode);
    }


}
