package warehouse.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DUNG
 */
@Controller
public class WarehouseController {
    
    @RequestMapping("/index")
    public String index(Model model) {
//        model.addAttribute("attribute", "value");
        return "warehouse/index";
    }
    @RequestMapping("/login")
    public String login(Model model) {
//        model.addAttribute("attribute", "value");
        return "login/login";
    }
}
