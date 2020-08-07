package com.example.bankmonolith.web.controller;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.domain.Transaction;
import com.example.bankmonolith.service.CustomerService;
import com.example.bankmonolith.service.TransactionService;
import com.example.bankmonolith.web.model.AccountCardInfo;
import com.example.bankmonolith.web.model.CustomerDto;
import com.example.bankmonolith.web.model.TransactionDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequestMapping("/transactions")
@Controller
public class TransactionController {
    @Autowired
    private TransactionService service;
    @RequestMapping(value = {"/all",""},method = RequestMethod.GET)
    public String findTransactions(Model model){
        model.addAttribute("transactions",service.findAll());
        return "transactions/findTransactions";
    }

    @RequestMapping(value = {"/find"},method = RequestMethod.GET)
    public String findTransaction(@RequestParam("transactionNumber") String transactionNumber, Model model){
        model.addAttribute("transaction",service.findByTransactionNumber(transactionNumber));
        return "transactions/detailsTransaction";
    }

    @GetMapping("/{transactionId}")
    public ModelAndView showCustomer(@PathVariable UUID transactionId) throws ChangeSetPersister.NotFoundException {
        ModelAndView mav = new ModelAndView("transactions/detailsTransaction");
        mav.addObject("transaction",service.findById(transactionId));
        return mav;
    }

    @GetMapping("/{accountFromId}/{accountToId}/new")
    public String initCreateForm(@PathVariable("accountFromId") UUID accountFromId,@PathVariable("accountToId")UUID accountToId, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("accountFromId",service.findById(accountFromId));
        model.addAttribute("accountToId",service.findById(accountToId));
        return "transactions/createTransaction";
    }

    @PostMapping("/{accountFromId}/{accountToId}/new")
    public String processCreateForm(@PathVariable("accountFromId") UUID accountFromId, @PathVariable("accountToId")UUID accountToId,@RequestBody TransactionDto transactionDto,BindingResult result) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()){
            return "transactions/createTransaction";
        }
        TransactionDto save = service.saveTransaction(accountFromId,accountToId,transactionDto);
        return "redirect:/transactions/" + save.getId();
    }

    @GetMapping("/{transactionId}/edit")
    public String initUpdateForm(@PathVariable UUID transactionId, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("transaction",service.findById(transactionId));
        return "transactions/createTransaction";
    }

    @PostMapping("/{transactionId}/edit")
    public String processUpdateForm(@PathVariable UUID transactionId, TransactionDto transactionDto, BindingResult result) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()){
            return "transactions/createTransaction";
        }
        TransactionDto save = service.updateTransactions(transactionId,transactionDto);
        return "redirect:/transactions/" + save.getId();
    }

    @PostMapping("/{transactionId}/delete")
    public String customerDelete(@PathVariable UUID transactionId) throws ChangeSetPersister.NotFoundException {
        service.deleteTransactions(transactionId);
        return "redirect:/transactions/all";
    }


}
