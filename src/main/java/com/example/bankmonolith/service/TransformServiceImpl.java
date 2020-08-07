package com.example.bankmonolith.service;

import com.example.bankmonolith.domain.Account;
import com.example.bankmonolith.domain.Card;
import com.example.bankmonolith.domain.Transaction;
import com.example.bankmonolith.repository.AccountRepository;
import com.example.bankmonolith.repository.CardRepository;
import com.example.bankmonolith.repository.TransactionRepository;
import com.example.bankmonolith.web.mapper.CardMapper;
import com.example.bankmonolith.web.mapper.TransactionMapper;
import com.example.bankmonolith.web.model.AccountCardInfo;
import com.example.bankmonolith.web.model.TransactionDto;
import com.example.bankmonolith.web.model.TransformInfo;
import javassist.NotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransformServiceImpl implements TransformService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CardRepository cardRepository;
    @Override
    public AccountCardInfo getAccountCardInfo(AccountCardInfo accountCardInfo) throws ChangeSetPersister.NotFoundException {
        Card fromCard = cardRepository.findByCardNumber(accountCardInfo.getCardFrom().getCardNumber()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Card toCard = cardRepository.findByCardNumber(accountCardInfo.getCardToCardNumber()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        accountCardInfo.setNameFrom(fromCard.getAccount().getCustomer().getFirstName() + " " +  fromCard.getAccount().getCustomer().getLastName() );
        accountCardInfo.setNameTo(toCard.getAccount().getCustomer().getFirstName() + " " +  toCard.getAccount().getCustomer().getLastName() );
        return accountCardInfo;
    }

    @Override
    public TransformInfo saveTransform(AccountCardInfo accountCardInfo) throws ChangeSetPersister.NotFoundException {
        Card fromCard = cardRepository.findByCardNumber(accountCardInfo.getCardFrom().getCardNumber()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Card toCard = cardRepository.findByCardNumber(accountCardInfo.getCardToCardNumber()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Account formAccount = fromCard.getAccount();
        Account toAccount = toCard.getAccount();
        if (formAccount.getBalance().subtract(accountCardInfo.getBalance()).compareTo(BigDecimal.ZERO)<0){
            throw new RuntimeException("Balance not enough.");
        }
        formAccount.setBalance(formAccount.getBalance().subtract(accountCardInfo.getBalance()));
        toAccount.setBalance(toAccount.getBalance().add(accountCardInfo.getBalance()));
        Transaction save = transactionRepository.save(Transaction.builder().accountFrom(formAccount).accountTo(toAccount)
                .transactionNumber(UUID.randomUUID().toString()).totalBalance(accountCardInfo.getBalance()).transactionType(0).build());
        return TransformInfo.builder().transactionNumber(save.getTransactionNumber())
                .fromName(formAccount.getCustomer().getFirstName() +" "+ formAccount.getCustomer().getLastName())
                .toName(toAccount.getCustomer().getFirstName() +" "+ formAccount.getCustomer().getLastName()).balance(accountCardInfo.getBalance()).build();
    }
}
