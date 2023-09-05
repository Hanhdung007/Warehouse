package warehouse.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.model.Customers;

import java.util.List;
import java.util.Optional;
import warehouse.exam.demo.reponsitory.CustomersRepository;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    @Autowired
    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Optional<Customers> getCustomerByID(Integer customerID) {
        return customersRepository.findById(customerID);
    }

    public Customers saveCustomer(Customers customer) {
        return customersRepository.save(customer);
    }

    public void deleteCustomer(Integer customerID) {
        customersRepository.deleteById(customerID);
    }
}
