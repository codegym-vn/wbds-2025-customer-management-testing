package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImplWithSpringData extends CustomerService {
    
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImplWithSpringData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageInfo) {
        return customerRepository.findAll(pageInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> search(String keyword) {
        return customerRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrAddressContainingIgnoreCase(
                keyword, keyword, keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> search(String keyword, Pageable pageInfo) {
        return customerRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrAddressContainingIgnoreCase(
                keyword, keyword, keyword, pageInfo);
    }

    @Override
    protected JpaRepository<Customer, Long> repository() {
        return customerRepository;
    }
}
