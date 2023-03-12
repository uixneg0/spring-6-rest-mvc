package guru.springframework.spring6restmvc;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BootstrapDataTest {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        this.bootstrapData = new BootstrapData(beerRepository, customerRepository);
    }

    @Test
    void testBootstrap() throws Exception {
        bootstrapData.run();
        for (Customer customer : customerRepository.findAll()){
            System.out.println(customer);
        }

        for (Beer beer  : beerRepository.findAll()){
            System.out.println(beer);
        }
    }
}