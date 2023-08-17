/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.service.ImportService;

/**
 *
 * @author DUNG
 */
@RequestMapping("/import")
@Controller
public class ImportOrderController {
    @Autowired
    ImportService service;
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", service.getAll());
        return "import/index";
    }
    @GetMapping("/create")
   public String create(Model model) {
        model.addAttribute("import", new Importorders());
        return "import/create";
    }
   @PostMapping("/create")
    public String create(Model model, @ModelAttribute importDAO dao) {
        dao.setStatus(false);
        service.saveImportOrder(dao);
        return "redirect: /import/index ";
    }
    @GetMapping("/{id}")
   public String create(Model model, @PathVariable ("id") int id) {
        model.addAttribute("import", new Importorders());
        return "import/create";
    }
}
