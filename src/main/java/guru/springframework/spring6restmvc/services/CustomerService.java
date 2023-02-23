package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();

    Customer getCustomerByUUID(UUID id);

    Customer saveNewCustomer(Customer customer);

    Customer updateCustomer(UUID id, Customer customer);

    void deleteById(UUID id);

    Customer patchCustomer(UUID id, Customer customer);
}
