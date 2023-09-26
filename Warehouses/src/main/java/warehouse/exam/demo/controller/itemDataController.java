/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import warehouse.exam.demo.DAL.itemdataDAO;
import warehouse.exam.demo.model.Itemdatas;
import warehouse.exam.demo.service.itemdataService;

import javax.servlet.http.HttpSession;
import warehouse.exam.demo.reponsitory.itemdataReponsitory;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/itemdata")
public class itemDataController {

    @Autowired
    itemdataService itemService;
    @Autowired
    itemdataReponsitory itemreponsitory;
    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping("/index")
    @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
    public String index(Model model, HttpSession session) {
        model.addAttribute("list", itemService.getAll());
        model.addAttribute("itemdata", new Itemdatas());
        return "itemdata/index";
    }

    @GetMapping("/create")
     @PreAuthorize("hasAnyRole('admin', 'sale', 'importer')")
    public String create(Model model, HttpSession session) {
        model.addAttribute("itemdata", new Itemdatas());
        return "itemdata/create";
    }

    @PostMapping("/create")
     @PreAuthorize("hasAnyRole('admin', 'sale', 'importer')")
    public String create(Model model, @ModelAttribute itemdataDAO itemData, @RequestParam("file") MultipartFile file) throws IOException {
        Optional<Itemdatas> loc = itemreponsitory.findById(itemData.getCode());
        Itemdatas item = itemreponsitory.findByName(itemData.getName());
        if(loc.isPresent()){
            return "redirect:/itemdata/create";
        }
        if(item != null){
            return "redirect:/itemdata/create";
        }
        String fileName = file.getOriginalFilename();
//         Path pathToFile = Paths.get(filename);
        FileCopyUtils.copy(file.getBytes(), new File(fileUpload, fileName));
        itemData.setImage(fileName);
        itemService.saveItemData(itemData);
        return "redirect:/itemdata/index";
    }

    @GetMapping("/update/{code}")
     @PreAuthorize("hasAnyRole('admin', 'sale', 'importer')")
    public String update(Model model, @PathVariable("code") String code) {
        Itemdatas item = itemService.findOne(code);
        model.addAttribute("itemdata", item);
        return "itemdata/edit";
    }

    @PostMapping("/update")
     @PreAuthorize("hasAnyRole('admin', 'sale', 'importer')")
    public String update(Model model, @ModelAttribute itemdataDAO itemData, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getSize() > 0) {
            String fileName = file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload, fileName));
            itemData.setImage(fileName);
        }
        itemService.updateItemData(itemData, itemData.getCode());
        return "redirect:/itemdata/index";
    }
}
