package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Getter
    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl(){
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.
                builder()
                .uuid(UUID.randomUUID())
                .customerName("Customer name1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.
                builder()
                .uuid(UUID.randomUUID())
                .customerName("Customer name2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.
                builder()
                .uuid(UUID.randomUUID())
                .customerName("Customer name2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        addCustomers(customer1, customer2, customer3);
    }

    private void addCustomers(Customer... customers){
        for (Customer customer : customers){
            this.getCustomerMap().put(customer.getUuid(), customer);
        }
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(this.getCustomerMap().values());
    }

    @Override
    public Customer getCustomerByUUID(UUID id) {
        return this.getCustomerMap().getOrDefault(id, null);
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {
        customer.setUuid(UUID.randomUUID());
        customer.setVersion(1);
        customer.setCreatedDate(LocalDateTime.now());
        customer.setLastModifiedDate(LocalDateTime.now());
        this.getCustomerMap().put(customer.getUuid(), customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        Customer existing = this.getCustomerMap().getOrDefault(id, customer);
        if (existing != null){
            existing.setCustomerName(customer.getCustomerName());
            existing.setLastModifiedDate(LocalDateTime.now());
        }
        this.getCustomerMap().put(id, existing);
        return existing;
    }

    @Override
    public void deleteById(UUID id) {
        this.getCustomerMap().remove(id);
    }

    @Override
    public Customer patchCustomer(UUID id, Customer customer) {
        Customer currentCustomer = this.getCustomerMap().get(id);
        if (currentCustomer != null){
            if (StringUtils.hasText(currentCustomer.getCustomerName())){
                currentCustomer.setCustomerName(customer.getCustomerName());
            }
        }
        this.getCustomerMap().put(id, currentCustomer);
        return currentCustomer;
    }
}
