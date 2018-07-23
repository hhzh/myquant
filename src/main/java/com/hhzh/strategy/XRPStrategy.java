package com.hhzh.strategy;

import com.hhzh.service.IFutureTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XRPStrategy {

    @Autowired
    private IFutureTradeService futureTradeService;

    public void executeStrategy() {

    }

}
