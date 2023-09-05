package warehouse.exam.demo.controllerAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouse.exam.demo.model.Customers;
import warehouse.exam.demo.service.CustomersService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomersAPIController {
    private final CustomersService customersService;

    @Autowired
    public CustomersAPIController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> customers = customersService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<Customers> getCustomerByID(@PathVariable Integer customerID) {
        Optional<Customers> customer = customersService.getCustomerByID(customerID);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Customers> addCustomer(@RequestBody Customers customer) {
        Customers savedCustomer = customersService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{customerID}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable Integer customerID, @RequestBody Customers customer) {
        if (!customersService.getCustomerByID(customerID).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setCustomerID(customerID);
        Customers updatedCustomer = customersService.saveCustomer(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
}
