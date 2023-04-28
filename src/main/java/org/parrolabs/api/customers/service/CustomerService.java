package org.parrolabs.api.customers.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.parrolabs.api.model.customers.Customers;
import org.parrolabs.api.model.orders.Orders;
import org.parrolabs.api.repository.customers.CustomersRepository;
import org.parrolabs.api.repository.orders.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public CustomerService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Customers getCustomerById(Long id) {
        return customersRepository.findById(id).get();
    }

    public Customers saveCustomer(Customers customers) {
        Customers newCustomer = new Customers(-1L,
                customers.getName(),
                customers.getPhone(),
                customers.getEmail(),
                customers.getAddress());
        return customersRepository.save(newCustomer);
    }

    public Customers updateCustomer(Long id, Customers customers) {

        if (!customersRepository.existsById(id)) {
            throw new EntityNotFoundException("No Customer found by id: " + id);
        }
        customers.setId(id);
        return customersRepository.save(customers);
    }

    public Boolean deleteCustomer(Long id) throws Exception {

        List<Orders> orders = ordersRepository.findByCustomerId(id);

        if (!orders.isEmpty()) {
            throw new Exception("Customer can´t be deleted because has been used in an order.");
        }

        if (customersRepository.existsById(id)) {
            customersRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
