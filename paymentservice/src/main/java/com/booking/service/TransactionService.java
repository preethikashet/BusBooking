package com.booking.service;

import com.booking.entity.TransactionEntity;
import com.booking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionService {
    @Autowired
    public TransactionRepository transactionRepository;

    public String addtransaction(TransactionEntity transactionEntity)
    {
        transactionRepository.save(transactionEntity);
        return "done";
    }

    public List<TransactionEntity> getallpayments(){
        return transactionRepository.findAll();
    }
}
