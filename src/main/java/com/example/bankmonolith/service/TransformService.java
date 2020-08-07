package com.example.bankmonolith.service;

import com.example.bankmonolith.web.model.AccountCardInfo;
import com.example.bankmonolith.web.model.TransactionDto;
import com.example.bankmonolith.web.model.TransformInfo;
import javassist.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface TransformService {
    AccountCardInfo getAccountCardInfo(AccountCardInfo accountCardInfo) throws ChangeSetPersister.NotFoundException;
    TransformInfo saveTransform(AccountCardInfo accountCardInfo) throws ChangeSetPersister.NotFoundException;
}
