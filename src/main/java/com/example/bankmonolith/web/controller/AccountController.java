package com.example.bankmonolith.web.controller;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.service.AccountService;
import com.example.bankmonolith.web.model.AccountDto;
import com.example.bankmonolith.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping(value = {"/all",""},method = RequestMethod.GET)
    public String findAccounts(Model model){
        model.addAttribute("accounts",accountService.findAll());
        return "accounts/findAccounts";
    }

    @RequestMapping(value = {"find"},method = RequestMethod.GET)
    public String findAccounts(@RequestParam("accountNumber")String accountNumber,Model model){
        model.addAttribute("accounts",accountService.findByAccountNumber(accountNumber));
        return "accounts/findAccounts";
    }

    @GetMapping("/{accountId}")
    public ModelAndView showAccount(@PathVariable UUID accountId) throws ChangeSetPersister.NotFoundException {
        ModelAndView mav = new ModelAndView("accounts/detailsAccount");
        mav.addObject("account",accountService.findById(accountId));
        return mav;
    }

    @GetMapping("/{customerId}/new")
    public ModelAndView initCreationForm(@PathVariable UUID customerId) throws ChangeSetPersister.NotFoundException {
        ModelAndView mav = new ModelAndView("accounts/createAccount");
        mav.addObject("account", AccountDto.builder().build());
        mav.addObject("customerId", customerId);
        return mav;
    }
    @PostMapping("/{customerId}/new")
    public String processCreateForm(@PathVariable UUID customerId,AccountDto accountDto,BindingResult result) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()){
            return "accounts/createAccount";
        }
        AccountDto save = accountService.saveAccount(customerId,accountDto);
        return "redirect:/accounts/"+save.getId();
    }

    @GetMapping("/{accountId}/edit")
    public String initUpdateForm(@PathVariable UUID accountId, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("account",accountService.findById(accountId));
        return "accounts/createAccount";
    }

    @PostMapping("/{accountId}/edit")
    public String processUpdateForm(@PathVariable UUID accountId, AccountDto accountDto, BindingResult result) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()){
            return "accounts/createAccount";
        }
        AccountDto save = accountService.updateAccount(accountId, accountDto);
        return "redirect:/accounts/"+save.getId();
    }

    @PostMapping("/{accountId}/delete")
    public String accountDelete(@PathVariable UUID accountId) throws ChangeSetPersister.NotFoundException {
        accountService.deleteAccount(accountId);
        return "redirect:/";
    }



}
