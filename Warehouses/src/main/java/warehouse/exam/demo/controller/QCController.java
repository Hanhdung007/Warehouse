package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
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
    public String search(@RequestParam("keyword") String keyword, RedirectAttributes redirectAttributes) {
        List<itemmasterDAO> foundOrders = qcService.searchItem(keyword);
        redirectAttributes.addFlashAttribute("searchResults", foundOrders);
        return "redirect:/qc/index";
    }

    @PostMapping("/accept/{id}")
    public String AcceptedQuantity(@Nullable @RequestParam String accept,@Nullable @RequestParam String inject, @RequestParam int id, @RequestParam int quantityInput){
        if(accept != null){
            qcService.AcceptQuantity(id, quantityInput);
        }
        else if(inject != null){
            qcService.InjectQuantity(id);
        }
        return "redirect:/qc/index";
    }

    @PostMapping("/inject/{id}")
    public String InjectQuantity(@RequestParam int id){
        qcService.InjectQuantity(id);
        return "redirect:/qc/index";
    }


//    private final String qcUrl = "http://localhost:9999/api/qc";
//
//    @GetMapping("/index")
//    public String index(Model model) {
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            // Gọi API /api/qc/index
//            ResponseEntity<List> indexResponse = restTemplate.getForEntity(qcUrl + "/index", List.class);
//            // Xử lý kết quả từ API /api/qc/index
//            List<itemmasterDAO> itemList = indexResponse.getBody();
//            model.addAttribute("list", itemList);
//        } catch (Exception ex) {
//            // Xử lý lỗi và ném ngoại lệ ResponseStatusException với mã lỗi HTTP 500
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
//        }
//        return "qc/index";
//    }
//    @GetMapping("/index")
//    public String index(Model model, HttpSession session) {
//        // Kiểm tra biến session
//        Boolean loggedInUser = (Boolean) session.getAttribute("loggedInUser");
//        if (loggedInUser == null || !loggedInUser) {
//            // Người dùng chưa đăng nhập, chuyển hướng đến trang login
//            return "redirect:/login";
//        }
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cookie", "JSESSIONID=" + session.getId());
//        ParameterizedTypeReference<List<Customer>> responseType = new ParameterizedTypeReference<List<Customer>>() {
//        };
//        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);
//        ResponseEntity<List<Customer>> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
//
//        model.addAttribute("list", response.getBody());
//        return "index";
//    }
}
