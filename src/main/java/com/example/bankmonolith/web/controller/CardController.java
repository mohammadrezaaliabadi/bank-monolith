package com.example.bankmonolith.web.controller;

import com.example.bankmonolith.domain.Card;
import com.example.bankmonolith.service.CardService;
import com.example.bankmonolith.web.model.CardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService service;
    @RequestMapping(value = {"/all",""},method = RequestMethod.GET)
    public String findCards(Model model){
        model.addAttribute("cards",service.findAll());
        return "cards/findCards";
    }

    @RequestMapping(value = {"find"},method = RequestMethod.GET)
    public String findAccounts(@RequestParam("cardNumber")String cardNumber,Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("cards",service.findByCardNumber(cardNumber));
        return "cards/findCards";
    }

    @GetMapping("/{cardId}")
    public ModelAndView showAccount(@PathVariable UUID cardId) throws ChangeSetPersister.NotFoundException {
        ModelAndView mav = new ModelAndView("cards/detailsCard");
        mav.addObject("card",service.findById(cardId));
        return mav;
    }

    @GetMapping("/{accountId}/new")
    public ModelAndView initCreationForm(@PathVariable UUID accountId) throws ChangeSetPersister.NotFoundException {
        ModelAndView mav = new ModelAndView("cards/createCard");
        mav.addObject("card", Card.builder().build());
        mav.addObject("accountId", accountId);
        return mav;
    }
    @PostMapping("/{cardId}/new")
    public String processCreateForm(@PathVariable UUID cardId, CardDto cardDto, BindingResult result) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()){
            return "cards/createCard";
        }
        CardDto save = service.saveCard(cardId,cardDto);
        return "redirect:/cards/"+save.getId();
    }

    @GetMapping("/{cardId}/edit")
    public String initUpdateForm(@PathVariable UUID cardId, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("card",service.findById(cardId));
        return "cards/createCard";
    }

    @PostMapping("/{cardId}/edit")
    public String processUpdateForm(@PathVariable UUID cardId, CardDto cardDto, BindingResult result) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()){
            return "cards/createCard";
        }
        CardDto save = service.updateCard(cardId, cardDto);
        return "redirect:/cards/"+save.getId();
    }

    @PostMapping("/{cardId}/delete")
    public String delete(@PathVariable UUID cardId) throws ChangeSetPersister.NotFoundException {
        service.deleteCard(cardId);
        return "redirect:/";
    }

}
