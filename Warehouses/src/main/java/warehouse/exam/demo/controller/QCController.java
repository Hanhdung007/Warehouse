package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.QCService;

import java.text.ParseException;
import java.util.List;

@RequestMapping("/qc")
@Controller
public class QCController {

    @Autowired
    QCService qcService;

    @GetMapping("/index")
    public String index(Model model) throws ParseException {
        List<itemmasterDAO> searchList = (List<itemmasterDAO>) model.asMap().get("searchResults");
        if (searchList != null) {
            model.addAttribute("list", searchList);
        } else {
            model.addAttribute("list", qcService.getAll());
        }
        return "qc/index";
    }

    @PostMapping("/accept")
    public String AcceptedQuantity(@RequestParam int id, @RequestParam int quantityInput){
        qcService.AcceptQuantity(id, quantityInput);
        return "redirect:/qc/index";
    }

    @PostMapping("/inject")
    public String InjectQuantity(@RequestParam int id){
        qcService.InjectQuantity(id);
        return "redirect:/qc/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, RedirectAttributes redirectAttributes) {
        List<itemmasterDAO> foundOrders = qcService.searchItem(keyword);
        redirectAttributes.addFlashAttribute("searchResults", foundOrders);
        return "redirect:/qc/index";
    }
}
