package guru.springframework.spring6restmvc;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Component
public class BootstrapData implements CommandLineRunner {
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    public BootstrapData(BeerRepository beerRepository, CustomerRepository customerRepository) {
        this.beerRepository = beerRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CLR running");
        Customer customer1 = Customer.
                builder().
                name("Name 1").createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).
                build();

        Customer customer2 = Customer.
                builder().
                name("Name 2").createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).
                build();

        Beer beer1 = Beer.builder().beerName("Beer name 1").createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).beerStyle(BeerStyle.ALE).quantityOnHand(1000).price(BigDecimal.valueOf(1000)).upc("upc1").build();

        Beer beer2 = Beer.builder().beerName("Beer name 2").createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).beerStyle(BeerStyle.ALE).quantityOnHand(1000).price(BigDecimal.valueOf(1000)).upc("upc2").build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        beerRepository.save(beer1);
        beerRepository.save(beer2);

    }
}
