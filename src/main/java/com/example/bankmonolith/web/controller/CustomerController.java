package com.example.bankmonolith.web.controller;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.service.CustomerService;
import com.example.bankmonolith.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RequestMapping("/customers")
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = {"/all",""},method = RequestMethod.GET)
    public String findCustomers(Model model){
        model.addAttribute("customers",customerService.findAll());
        return "customers/findCustomers";
    }

    @RequestMapping(value = {"/find"},method = RequestMethod.GET)
    public String findCustomers(@RequestParam("lastName") String lastName, Model model){
        model.addAttribute("customers",customerService.findByLastName(lastName));
        return "customers/findCustomers";
    }

    @GetMapping("/{customerId}")
    public ModelAndView showCustomer(@PathVariable UUID customerId) throws ChangeSetPersister.NotFoundException {
        ModelAndView mav = new ModelAndView("customers/customerDetails");
        mav.addObject("customer",customerService.findById(customerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("customer", CustomerDto.builder().build());
        return "customers/createCustomer";
    }
    @PostMapping("/new")
    public String processCreationForm(CustomerDto customerDto,BindingResult result){
        if (result.hasErrors()){
            return "customers/createCustomer";
        }
        CustomerDto save = customerService.saveCustomer(customerDto);
        return "redirect:/customers/" + save.getId();
    }

    @GetMapping("/{customerId}/edit")
    public String initUpdateForm(@PathVariable UUID customerId, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("customer",customerService.findById(customerId));
        return "customers/createCustomer";
    }

    @PostMapping("/{customerId}/edit")
    public String processUpdateForm(@PathVariable UUID customerId,CustomerDto customerDto, BindingResult result) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()){
            return "customers/createCustomer";
        }
        CustomerDto save = customerService.updateCustomer(customerId,customerDto);
        return "redirect:/customers/" + save.getId();
    }

    @PostMapping("/{customerId}/delete")
    public String customerDelete(@PathVariable UUID customerId) throws ChangeSetPersister.NotFoundException {
        customerService.deleteCustomer(customerId);
        return "redirect:/customers/all";
    }

}
