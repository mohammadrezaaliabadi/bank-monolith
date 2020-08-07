package com.example.bankmonolith.web.controller;

import com.example.bankmonolith.service.TransformService;
import com.example.bankmonolith.web.model.AccountCardInfo;
import com.example.bankmonolith.web.model.TransactionDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/transform")
public class TransformController {
    @Autowired
    private TransformService service;

    @PostMapping("/getInfo")
    public ResponseEntity<AccountCardInfo> getCardInfo(@RequestBody AccountCardInfo accountCardInfo) throws NotFoundException, ChangeSetPersister.NotFoundException {
        return new ResponseEntity(service.getAccountCardInfo(accountCardInfo), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity postCardInfo(@RequestBody AccountCardInfo accountCardInfo) throws NotFoundException, ChangeSetPersister.NotFoundException {
        return new ResponseEntity(service.saveTransform(accountCardInfo),HttpStatus.OK);
    }
}
