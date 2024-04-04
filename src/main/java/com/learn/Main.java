package com.learn;

import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping(path = "api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public record NewCustomerRequest(String name,
                              String email,
                              Integer age) {
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId) {
        customerRepository.deleteById(customerId);
    }

//    public class UpdateCustomerRequest {
//        private String name;
//        private String email;
//        private Integer age;
//
//        // getters and setters'
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public Integer getAge() {
//            return age;
//        }
//
//        public void setAge(Integer age) {
//            this.age = age;
//        }
//    }
//
//    @Transactional
//    @PutMapping(path = "{customerId}")
//    public void updateCustomer(@PathVariable("customerId") Integer customerId,
//                               @RequestBody UpdateCustomerRequest request) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "customer with id " + customerId + " does not exist"));
//        if (request.getName() != null) {
//            customer.setName(request.getName());
//        }
//        if (request.getEmail() != null) {
//            customer.setEmail(request.getEmail());
//        }
//        if (request.getAge() != null) {
//            customer.setAge(request.getAge());
//        }
//        customerRepository.save(customer);
//    }
}

