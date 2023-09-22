package warehouse.exam.demo.controller;

import org.springframework.stereotype.Controller;
import warehouse.exam.demo.reponsitory.CustomErrorRepository;

@Controller
public class CustomErrorController implements CustomErrorRepository {
    @Override
    public String getErrorPath() {
        return "/error";
    }
}