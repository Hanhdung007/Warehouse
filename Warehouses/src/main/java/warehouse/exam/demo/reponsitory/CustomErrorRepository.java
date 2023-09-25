package warehouse.exam.demo.reponsitory;

import org.springframework.boot.web.servlet.error.ErrorController;

public interface CustomErrorRepository extends ErrorController {
    String getErrorPath();
}
