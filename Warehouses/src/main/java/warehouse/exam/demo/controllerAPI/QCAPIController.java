package warehouse.exam.demo.controllerAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.service.QCService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/qc")
public class QCAPIController {
//    private final QCService qcService;
//
//    public QCAPIController(QCService qcService) {
//        this.qcService = qcService;
//    }
//
//    @GetMapping("/index")
//    public ResponseEntity<List<itemmasterDAO>> index() throws ParseException {
//        try {
//            List<itemmasterDAO> itemList = qcService.getAll();
//            return ResponseEntity.ok(itemList);
//        } catch (Exception ex) {
//            // Nếu có lỗi, trả về mã lỗi HTTP 500 (Internal Server Error)
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
}
