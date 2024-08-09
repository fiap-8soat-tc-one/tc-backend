package com.fiap.tc.adapters.repository.output;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driven.infrastructure.outputs.CustomerOutputAdapter;
import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.CustomerRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.CustomerEntity;
import com.fiap.tc.core.domain.exceptions.NotFoundException;
import com.fiap.tc.adapters.driver.presentation.requests.CustomerRequest;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerOutputAdapterTest extends FixtureTest {

    public static final String DOCUMENT = "11111111111";
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerOutputAdapter customerOutputAdapter;

    private CustomerRequest request;

    private CustomerEntity customerEntity;

    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        customerEntity = Fixture.from(CustomerEntity.class).gimme("valid");
        pageable = Mockito.mock(Pageable.class);
        request = Fixture.from(CustomerRequest.class).gimme("valid");
    }

    @Test
    public void deleteCustomerTest() {

        when(customerRepository.findByDocument(DOCUMENT)).thenReturn(Optional.of(customerEntity));

        customerOutputAdapter.delete(DOCUMENT);

        verify(customerRepository).delete(customerEntity);

    }

    @Test
    public void listCustomersTest() {

        final var customerEntities = new PageImpl<CustomerEntity>(List.of(customerEntity));
        when(customerRepository.findAll(pageable)).thenReturn(customerEntities);

        var customers = customerOutputAdapter.list(pageable);

        assertEquals(1, customers.getSize());
        verify(customerRepository).findAll(pageable);

    }

    @Test
    public void loadCustomerTest() {
        when(customerRepository.findByDocument(DOCUMENT)).thenReturn(Optional.of(customerEntity));

        var customer = customerOutputAdapter.load(DOCUMENT);
        assertNotNull(customer);
        verify(customerRepository).findByDocument(DOCUMENT);

    }

    @Test
    public void customerNotFoundTest() {

        when(customerRepository.findByDocument(DOCUMENT)).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> customerOutputAdapter.load(DOCUMENT));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

    @Test
    public void customerSaveTest() {

        when(customerRepository.findByDocument(request.getDocument())).thenReturn(Optional.empty());
        when(customerRepository.save(Mockito.any())).thenReturn(customerEntity);

        var customer = customerOutputAdapter.saveOrUpdate(request.getDocument(),
                request.getName(), request.getEmail());

        assertNotNull(customer);
        verify(customerRepository).findByDocument(request.getDocument());
        verify(customerRepository).save(Mockito.any());
    }

    @Test
    public void customerUpdateTest() {

        when(customerRepository.findByDocument(request.getDocument())).thenReturn(Optional.of(customerEntity));
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);

        var customer = customerOutputAdapter.saveOrUpdate(request.getDocument(),
                request.getName(), request.getEmail());

        assertNotNull(customer);
        verify(customerRepository).findByDocument(request.getDocument());
        verify(customerRepository).save(Mockito.any());
    }

}