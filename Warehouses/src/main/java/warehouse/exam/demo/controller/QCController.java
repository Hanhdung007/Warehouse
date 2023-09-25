package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.service.QCService;

import java.text.ParseException;
import java.util.List;

@RequestMapping("/qc")
@Controller
public class QCController {

    @Autowired
    QCService qcService;

    @GetMapping("/index")
    @PreAuthorize("hasRole('qc')")
    public String index(Model model) throws ParseException {
        List<itemmasterDAO> searchList = (List<itemmasterDAO>) model.asMap().get("searchResults");
        if (searchList != null) {
            model.addAttribute("list", searchList);
        } else {
            model.addAttribute("list", qcService.getAll());
        }
        return "qc/index";
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('qc')")
    public String search(@RequestParam("keyword") String keyword, RedirectAttributes redirectAttributes) {
        List<itemmasterDAO> foundOrders = qcService.searchItem(keyword);
        redirectAttributes.addFlashAttribute("searchResults", foundOrders);
        return "redirect:/qc/index";
    }

    @PostMapping("/accept/{id}")
    @PreAuthorize("hasRole('qc')")
    public String AcceptedQuantity(@Nullable @RequestParam String accept,@Nullable @RequestParam String inject, @RequestParam int id, @RequestParam int quantityInput, @RequestParam String qcBy){
        if(accept != null){
            qcService.AcceptQuantity(id, quantityInput, qcBy);
        } else if (inject != null) {
            qcService.InjectQuantity(id, quantityInput, qcBy);
        }
        return "redirect:/qc/index";
    }
}
