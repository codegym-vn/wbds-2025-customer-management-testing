package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CustomerServiceTestConfig.class)
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    public void resetMocks(){
        Mockito.reset(customerRepository);
    }

    @Test
    void testFindAll(){
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "CodeGym", "codegym@codegym.vn", "Hn"));
        PageRequest pageInfo =  PageRequest.of(0, 25);
        Page<Customer> customerPage = new PageImpl<>(customers,  pageInfo, 1);
        when(customerRepository.findAll( pageInfo)).thenReturn(customerPage);

        Page<Customer> actual = customerService.findAll(pageInfo);
        verify(customerRepository).findAll(pageInfo);
        assertEquals(customerPage, actual);
    }

    @Test
    void testFindByIdFound(){
        Customer customer = new Customer(1L, "CodeGym", "codegym@codegym.vn", "Hn");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Optional<Customer> actual =  customerService.findOne(1L);
        verify(customerRepository).findById(1L);
        assertEquals(Optional.of(customer),actual);
    }

    @Test
    void testFindByIdNotFound(){
        given(customerRepository.findById(1L)).willReturn(Optional.empty());
        Optional<Customer> actual = customerService.findOne(1L);
        verify(customerRepository).findById(1L);
        assertEquals(Optional.empty(), actual);
    }

    @Test
    void saveCustomer(){
        Customer customer = new Customer(1L, "CodeGym", "codegym@codegym.vn", "Hn");
        customerService.save(customer);
        verify(customerRepository).save(customer);
    }
}
