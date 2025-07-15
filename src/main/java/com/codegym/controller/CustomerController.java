package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ModelAndView showList(@RequestParam(name = "s", required = false) Optional<String> s, Pageable pageInfo) {
        ModelAndView modelAndView = new ModelAndView("/customers/list");
        Page<Customer> customers = s.isPresent() ? search(s.get(), pageInfo) : getPage(pageInfo);
        modelAndView.addObject("keyword", s.orElse(null));
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Page<Customer>> apiList(Pageable pageInfo) {
        Page<Customer> customers = customerService.findAll(pageInfo);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ModelAndView showInformation(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/customers/info");
        Optional<Customer> customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer.get());
        return modelAndView;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Customer> apiInformation(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findOne(id);
        HttpStatus status = !customer.isPresent() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(customer.get(), status);
    }

    @PostMapping
    public ModelAndView updateCustomer(@ModelAttribute Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/customers/info");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        }
        customerService.save(customer);
        return new ModelAndView("redirect:/customers");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> apiCreateCustomer(@RequestBody Customer customer,
                                                  BindingResult bindingResult,
                                                  UriComponentsBuilder ucBuilder) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        customerService.save(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> apiUpdateCustomer(@PathVariable("id") long id,
                                                      @RequestBody Customer customer) {
        Optional<Customer> originCustomer = customerService.findOne(id);

        if (!originCustomer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        originCustomer.get().setName(customer.getName());
        originCustomer.get().setEmail(customer.getEmail());
        originCustomer.get().setAddress(customer.getAddress());

        customerService.save(originCustomer.get());
        return new ResponseEntity<>(originCustomer.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<Void> apiDeleteCustomer(@PathVariable Long id) {
        Optional<Customer> target = customerService.findOne(id);

        if (!target.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customerService.delete(target.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Page<Customer> getPage(Pageable pageInfo) {
        return customerService.findAll(pageInfo);
    }

    private Page<Customer> search(String str, Pageable pageInfo) {
        return customerService.search(str, pageInfo);
    }
}
